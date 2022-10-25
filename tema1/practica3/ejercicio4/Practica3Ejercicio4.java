public class Practica3Ejercicio4
{
    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        ConsumerThread consumerThread = new ConsumerThread(buffer);
        ProducerThread producerThread = new ProducerThread(buffer);
        consumerThread.start();
        producerThread.start();
    }
}
