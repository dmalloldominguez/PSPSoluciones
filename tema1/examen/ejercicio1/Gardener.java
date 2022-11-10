package ejercicio1;

import static java.lang.System.*;

public class Gardener extends Thread{
    Town town;
    int workedParks = 0;
    public Gardener(Town town) {
        this.town = town;
    }

    public int getWorkedParks() {
        return workedParks;
    }

    @Override
    public void run() {
        int park;
        long startTime = currentTimeMillis();
        while (currentTimeMillis() - startTime < Main.DURATION) {
            park = this.town.workPark();
            this.workedParks++;
            out.printf("El jardinero %d ha empezado a trabajar en el jardín %d\r\n", this.getId(), park);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            out.printf("El jardinero %d ha terminado de trabajar en el jardín %d\r\n", this.getId(), park);
            this.town.freePark(park);
        }
    }
}
