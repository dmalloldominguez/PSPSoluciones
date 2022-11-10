package ejercicio2;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static final int NGARDENERS = 3;
    static final int NPARKS = 10;
    static final int NCITIZENS = 5;
    static final long DURATION = 30000; // 30 seconds

    public static void main(String[] args) {
        Town town = new Town(NPARKS);
        List<String> complaints = new ArrayList<String>();

        Gardener[] gardeners = new Gardener[NGARDENERS];
        for (int i = 0; i < NGARDENERS; i++) {
            gardeners[i] = new Gardener(town);
        }

        Citizen[] citizens = new Citizen[NCITIZENS];
        for (int i = 0; i < NCITIZENS; i++) {
            citizens[i] = new Citizen(town, complaints);
        }

        System.out.print("Empieza la simulación: \r\n");
        for (int i = 0; i < NGARDENERS; i++) {
            gardeners[i].start();
        }
        for (int i = 0; i < NCITIZENS; i++) {
            citizens[i].start();
        }
        for (int i = 0; i < NGARDENERS; i++) {
            try {
                gardeners[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        for (int i = 0; i < NCITIZENS; i++) {
            try {
                citizens[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.print("\r\nTrabajos realizados: \r\n");
        for (int i = 0; i < NGARDENERS; i++) {
            System.out.printf("El jardinero %d ha trabajado %d jardines\r\n", gardeners[i].getId(), gardeners[i].getGardensWorked());
        }

        System.out.print("\r\nQuejas recibidas: \r\n");
        for (String complaint: complaints) {
            System.out.printf("%s\r\n", complaint);
        }
    }
}
