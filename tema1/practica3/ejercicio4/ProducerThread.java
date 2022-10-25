public class ProducerThread extends Thread {
    private Buffer buffer;
    private static long sleepTime = 1500;
    public ProducerThread(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            this.buffer.put(i);
            System.out.printf("Producer puts : %d\r\n", i);
            i++;
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                System.err.printf("Run Error %s\r\n", e.getMessage());
            }
        }
    }
}
