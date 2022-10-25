import java.util.concurrent.atomic.AtomicInteger;

public class CustomThread3 extends Thread{
    AtomicInteger count;

    CustomThread3(AtomicInteger count) {
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < 500; i++) {
            this.count.getAndIncrement();
        }
    }
}
