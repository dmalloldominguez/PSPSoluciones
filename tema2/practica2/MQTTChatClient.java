package practica2;

import org.eclipse.paho.client.mqttv3.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.stream.Collectors;

public class MQTTChatClient {
    MqttClient client;
    String username;
    String baseUrl;
    String chatPath;

    public MQTTChatClient(String serverUrl, String baseURL, String username, String chatPath) {
        this.username = username;
        this.baseUrl = baseURL;
        this.chatPath = chatPath;
        String publisherId = UUID.randomUUID().toString();
        try {
            this.client = new MqttClient(serverUrl, publisherId);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }

    public void connect() throws MqttException{
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(5);
        client.connect(options);
        setCallbacks();
    }

    public void close() throws MqttException {
        client.disconnect();
        client.close();
    }

    public void sendMessage(String username, String message) throws MqttException {
        if (client.isConnected()) {
            String topic = getSendChatTopic(username);
            byte[] payload = message.getBytes();
            MqttMessage msg = new MqttMessage(payload);
            msg.setQos(0);
            msg.setRetained(true);
            client.publish(topic, msg);
        }
    }

    public String getChat(String username) throws IOException {
        Path filename = Path.of(chatPath, "chat" + username);
        BufferedReader br = Files.newBufferedReader(filename);
        return  br.lines().collect(Collectors.joining("\r\n"));
    }

    private void setCallbacks() throws MqttException {
        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable throwable) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                writeReceivedMessage(topic, mqttMessage);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                try {
                    String topic = iMqttDeliveryToken.getTopics()[0];
                    MqttMessage mqttMessage = iMqttDeliveryToken.getMessage();
                    writeSentMessage(topic, mqttMessage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        String[] topics = new String[]{"/%s/todos".formatted(baseUrl), "/%s/+/%s".formatted(baseUrl, username)};
        client.subscribe(topics, new int[]{0,0});
    }

    private String getSendChatTopic(String otherUser){
        String topic;
        if (otherUser.equals("todos")){
            topic = String.format("/%s/%s", baseUrl, otherUser);
        } else {
            topic = String.format("/%s/%s/%s", baseUrl, username, otherUser);
        }
        return topic;
    }

    private void writeReceivedMessage(String topic, MqttMessage mqttMessage) throws IOException {
        String[] topicSplit = topic.split("/");
        Path filename;
        filename = Path.of(chatPath, topicSplit[1] + topicSplit[2]);
        // El método write de BufferedWriter es ThreadSafe si no deberíamos crear una clase y protegerla con synchronized
        BufferedWriter bw = Files.newBufferedWriter(filename, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        bw.write("%s (%s): %s".formatted(topicSplit[2], LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM HH:mm")), mqttMessage.toString()));
        bw.newLine();
        bw.close();
        //Files.write(filename, mqttMessage.toString().getBytes(), StandardOpenOption.APPEND); // NIO No funciona, no sé la razón
    }

    private void writeSentMessage(String topic, MqttMessage mqttMessage) throws IOException {
        String[] topicSplit = topic.split("/");
        if (!topicSplit[2].equals("todos")) { // El chat todos se recibirá por subscribe
            Path filename = Path.of(chatPath, topicSplit[1] + topicSplit[3]);
            // El método write de BufferedWriter es ThreadSafe si no deberíamos crear una clase y protegerla con synchronized
            BufferedWriter bw = Files.newBufferedWriter(filename, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            bw.write("%s (%s): %s".formatted(username, LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM HH:mm")), mqttMessage.toString()));
            bw.newLine();
            bw.close();
        }
    }


}
