import java.io.*;
import java.net.Socket;
import java.util.Base64;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class TCPClientBase64 {
    public static final int PORT = 3000;

    public static void main(String[] args) throws IOException {
        Socket clientSocket = null;
        BufferedReader input = null;
        PrintWriter output = null;
        Scanner scanner = new Scanner(System.in);

        try {
            clientSocket=new Socket("localhost",PORT);
            input=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            output=new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())),true);
        } catch (IOException e) {
            System.err.println("No se ha podido conectar al socket");
            System.exit(-1);
        }

        System.out.println("Escribe un mensaje:");
        String message = scanner.nextLine();
        String messageBase64= String.format("#%s#", Base64.getEncoder().encodeToString(message.getBytes()));
        output.println(messageBase64);
        output.close();
        input.close();
        clientSocket.close();
    }
}
