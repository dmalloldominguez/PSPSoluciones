import org.eclipse.paho.client.mqttv3.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.UUID;

public class MQTTSubscribe {
    public static void main(String[] args)  throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter topic: ");

        String topic = scanner.nextLine();

        String publisherId = UUID.randomUUID().toString();
        IMqttClient publisher = null;
        try {
            publisher = new MqttClient("tcp://ec2-3-83-241-142.compute-1.amazonaws.com:1883", publisherId);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        try {
            publisher.connect(options);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }

        publisher.setCallback(new MqttCallback() {

            public void messageArrived(String topic, MqttMessage message) throws Exception {
                System.out.println("\nReceived a Message!" +
                        "\n\tTime:    " + LocalDateTime.now() +
                        "\n\tTopic:   " + topic +
                        "\n\tMessage: " + new String(message.getPayload()) +
                        "\n\tQoS:     " + message.getQos() + "\n");
            }

            public void connectionLost(Throwable cause) {
                System.out.println("Connection to Solace broker lost!" + cause.getMessage());
            }

            public void deliveryComplete(IMqttDeliveryToken token) {
            }

        });

        try {
            publisher.subscribe(topic, 0);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }

}
