import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Base64;

public class TCPServerBase64 {
    public static final int PORT = 3000;
    public static final String FILEPATH = "messages.txt";
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            System.out.println("No se puede conectar el socket: " + PORT);
            System.exit(-1);
        }
        Socket clientSocket;
        BufferedReader input;
        System.out.println("Escuchando por el puerto: " + serverSocket);
        while (true) {
            try {
                clientSocket = serverSocket.accept();
                input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String messageBase64 = input.readLine();
                if (messageBase64.charAt(0) == messageBase64.charAt(messageBase64.length() - 1) && messageBase64.charAt(0) == '#') {
                    messageBase64 = messageBase64.replace("#", "");
                    String message = new String(Base64.getDecoder().decode(messageBase64));
                    Path filename = Path.of(".", FILEPATH);
                    try (BufferedWriter bw = Files.newBufferedWriter(filename, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
                        bw.write(message);
                        bw.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                System.out.println("IOException: " + e.getMessage());
            }
        }
    }
}
