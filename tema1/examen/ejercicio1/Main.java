package ejercicio1;

public class Main {
    static final int NGARDENERS = 3;
    static final int NPARKS = 15;
    static final long DURATION = 30000; // 30 seconds

    public static void main(String[] args) {
        Town town = new Town(NPARKS);
        Gardener[] gardeners = new Gardener[NGARDENERS];
        for (int i = 0; i < NGARDENERS; i++) {
            gardeners[i] = new Gardener(town);
        }

        System.out.print("Empieza la simulación: \r\n");
        for (int i = 0; i < NGARDENERS; i++) {
            gardeners[i].start();
        }
        for (int i = 0; i < NGARDENERS; i++) {
            try {
                gardeners[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.print("\r\nHa finalizado la simulación: \r\n");
        for (int i = 0; i < NGARDENERS; i++) {
            System.out.printf("El jardinero %d ha trabajado %d jardines\r\n", gardeners[i].getId(), gardeners[i].getWorkedParks());
        }
    }
}
