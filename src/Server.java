import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Сервер ждет клиента...");

        try (Socket clientSocket = serverSocket.accept();
             InputStream inputStream = clientSocket.getInputStream();
             OutputStream outputStream = clientSocket.getOutputStream()) {

            System.out.println("Новое соединение: " + clientSocket.getInetAddress().toString());
            int request;
            while ((request = inputStream.read()) != -1) {
                System.out.println("прислал клиент: " + request);
                outputStream.write(++request);
                System.out.println("отправлено клиенту: " + request);
                outputStream.flush();
            }
            System.out.println("клиент отключился");
        }
    }
}