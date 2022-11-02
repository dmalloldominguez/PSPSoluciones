import java.util.Random;

public class Filosofo implements Runnable {
    GestorPalillos gestorPalillos;
    int posPalilloIzq, posPalilloDer;

    public Filosofo(GestorPalillos g, int pIzq, int pDer) {
        this.gestorPalillos = g;
        this.posPalilloDer = pDer;
        this.posPalilloIzq = pIzq;
    }

    public void run() {
        while (true) {
            boolean palillosCogidos;
            palillosCogidos = this.gestorPalillos.intentarCogerPalillos(posPalilloIzq, posPalilloDer);
            if (palillosCogidos) {
                comer();
                this.gestorPalillos.liberarPalillos(posPalilloIzq, posPalilloDer);
                dormir();
            }
        }
    }

    private void comer() {
        System.out.println("Filosofo " + Thread.currentThread().getName() + " comiendo");
        esperarTiempoAzar();
    }

    private void esperarTiempoAzar() {
        Random generador = new Random();
        int msAzar = generador.nextInt(3);
        try {
            Thread.sleep(msAzar);
        } catch (InterruptedException ex) {
            System.out.println("Fallo la espera");
        }
    }

    private void dormir() {
        System.out.println("Filosofo " + Thread.currentThread().getName() + " durmiendo (zzzzzz)");
        esperarTiempoAzar();
    }
}
