import static java.lang.Thread.*;

public class Productor implements Runnable {
    Cola colaCompartida;

    public Productor(Cola cola) {
        this.colaCompartida = cola;
    }

    public void run() {
        while (true) {
            int num = (int) (Math.random() * 10);
            while (colaCompartida.encolar(num) == false) {
                try {
                    sleep((int) (Math.random() * 3000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            System.out.println("Productor encoló el numero:" + num);
        }
    }
}