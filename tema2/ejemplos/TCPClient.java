import java.net.*;
import java.io.*;

import static java.lang.Thread.sleep;

public class TCPClient {
    public static void main(String[] args)  throws IOException {
        Socket clientSocket = null;
        BufferedReader input = null;
        PrintWriter output = null;

        // Creamos un socket en el lado cliente, enlazado con un
        // servidor que está en la misma máquina que el cliente
        // y que escucha en el puerto 4444
        try {
            clientSocket = new Socket("localhost", 4444);
            // Obtenemos el canal de entrada
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // Obtenemos el canal de salida
            output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())),true);
        } catch (IOException e) {
            System.err.println("No puede establer canales de E/S para la conexión");
            System.exit(-1);
        }
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        try {
            // La envia al servidor
            output.println("Hola!");
            // Envía a la salida estándar la respuesta del servidor
            String response = input.readLine();
            System.out.println("Respuesta servidor: " + response);

        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Libera recursos
        output.close();
        input.close();
        stdIn.close();
        clientSocket.close();

    }
}