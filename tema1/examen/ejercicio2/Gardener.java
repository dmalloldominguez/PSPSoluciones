package ejercicio2;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;

public class Gardener extends Thread{
    Town town;
    int gardensWorked = 0;
    public Gardener(Town town) {
        this.town = town;
    }

    public int getGardensWorked() {
        return gardensWorked;
    }

    @Override
    public void run() {
        int garden;
        long startTime = currentTimeMillis();
        while (currentTimeMillis() - startTime < Main.DURATION) {
            garden = this.town.workPark();
            this.gardensWorked++;
            out.printf("El jardinero %d ha empezado a trabajar en el jardín %d\r\n", this.getId(), garden);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            out.printf("El jardinero %d ha terminado de trabajar en el jardín %d\r\n", this.getId(), garden);
            this.town.freePark(garden);
        }
    }
}
