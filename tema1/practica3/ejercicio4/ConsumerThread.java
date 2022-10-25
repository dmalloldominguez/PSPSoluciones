import java.util.Random;

public class ConsumerThread extends Thread {
    private Buffer buffer;
    private static long sleepTime = 1500;

    public ConsumerThread(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            System.out.printf("Consumer gets: %d\r\n", buffer.get());
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                System.err.printf("Run Error %s\r\n", e.getMessage());
            }
        }
    }
}
