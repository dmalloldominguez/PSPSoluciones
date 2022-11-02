public class LanzadorProductoresConsumidores {

    public static void main(String[] args) throws InterruptedException {
        final int MAX_PRODUCTORES = 3;
        final int MAX_CONSUMIDORES = 2;
        final int MAX_ELEMENTOS = 10;

        Thread[] hilosProductor;
        Thread[] hilosConsumidor;

        hilosProductor = new Thread[MAX_PRODUCTORES];
        hilosConsumidor = new Thread[MAX_CONSUMIDORES];

        Cola colaCompartida = new Cola(MAX_ELEMENTOS);

        for (int i = 0; i < MAX_PRODUCTORES; i++) {
            Productor productor = new Productor(colaCompartida);
            hilosProductor[i] = new Thread(productor);
            hilosProductor[i].start();
        }

        for (int i = 0; i < MAX_CONSUMIDORES; i++) {
            Consumidor consumidor = new Consumidor(colaCompartida);
            hilosConsumidor[i] = new Thread(consumidor);
            hilosConsumidor[i].start();
        }

        for (int i = 0; i < MAX_PRODUCTORES; i++) {
            hilosProductor[i].join();
        }

        for (int i = 0; i < MAX_CONSUMIDORES; i++) {
            hilosConsumidor[i].join();
        }
    }

}