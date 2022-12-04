import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class UDPTimeServer {

    public static void main(String args[]) {

        try {

            DatagramSocket socketUDP = new DatagramSocket(6789);
            byte[] buffer = new byte[1000];

            while (true) {
                // Construimos el DatagramPacket para recibir peticiones
                DatagramPacket receivedDatagram = new DatagramPacket(buffer, buffer.length);

                // Leemos una petición del DatagramSocket
                socketUDP.receive(receivedDatagram);

                System.out.print("Datagrama recibido del host: " + receivedDatagram.getAddress());
                System.out.println(" desde el puerto remoto: " + receivedDatagram.getPort());

                String received = new String(receivedDatagram.getData()).substring(0, 4);
                if (received.compareTo("time") == 0) {
                    String time = String.format("%02d:%02d", LocalDateTime.now().getHour(), LocalDateTime.now().getMinute());
                    System.out.printf("Sending response: %s", time);
                    // Construimos el DatagramPacket para enviar la respuesta
                    DatagramPacket responseDatagram = new DatagramPacket(time.getBytes(), time.length(), receivedDatagram.getAddress(), receivedDatagram.getPort());
                    // Enviamos la respuesta, que es un eco
                    socketUDP.send(responseDatagram);
                }
            }

        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }

}