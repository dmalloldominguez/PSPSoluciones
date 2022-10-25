import java.util.concurrent.atomic.AtomicInteger;

public class Practica3Ejercicio3 {

    public static void main(String[] args) {
        AtomicInteger count = new AtomicInteger(0);

        CustomThread3 t1 = new CustomThread3(count);
        CustomThread3 t2 = new CustomThread3(count);
        CustomThread3 t3 = new CustomThread3(count);
        CustomThread3 t4 = new CustomThread3(count);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            System.out.println("Error");
        }

        System.out.printf("Count: %d", count.get());
    }
}


