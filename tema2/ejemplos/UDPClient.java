import java.net.*;
import java.io.*;

public class UDPClient {

    // Los argumentos proporcionan el mensaje y el nombre del servidor
    public static void main(String args[]) {

        try {
            DatagramSocket socketUDP = new DatagramSocket();
            byte[] message = "¡Hola!".getBytes();
            InetAddress serverHost = InetAddress.getByName("localhost");
            int serverPort = 6789;

            // Construimos un datagrama para enviar el mensaje al servidor
            DatagramPacket sendDatagram = new DatagramPacket(message, message.length, serverHost, serverPort);

            // Enviamos el datagrama
            socketUDP.send(sendDatagram);

            // Construimos el DatagramPacket que contendrá la respuesta
            byte[] buffer = new byte[1000];
            DatagramPacket answerDatagram = new DatagramPacket(buffer, buffer.length);
            socketUDP.receive(answerDatagram);

            // Enviamos la respuesta del servidor a la salida estandar
            String received = new String(answerDatagram.getData());
            System.out.print("Respuesta: ");
            System.out.println(received);

            // Cerramos el socket
            socketUDP.close();

        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }
}