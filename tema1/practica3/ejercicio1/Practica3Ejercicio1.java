public class Practica3Ejercicio1 {

    public static void main(String[] args) {
        CustomNumber count = new CustomNumber();
        count.setNumber(0);

        CustomThread t1 = new CustomThread(count);
        CustomThread t2 = new CustomThread(count);
        CustomThread t3 = new CustomThread(count);
        CustomThread t4 = new CustomThread(count);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        System.out.printf("Count: %d", count.getNumber());
    }
}


