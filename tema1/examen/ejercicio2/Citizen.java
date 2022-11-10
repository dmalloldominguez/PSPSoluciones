package ejercicio2;

import java.util.List;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;

public class Citizen extends Thread{
    Town town;
    List<String> complaints;
    int gardensWorked = 0;

    public Citizen(Town town, List<String> complaints) {
        this.town = town;
        this.complaints=complaints;
    }

    @Override
    public void run() {
        int garden;
        long startTime = currentTimeMillis();
        while (currentTimeMillis() - startTime < Main.DURATION) {
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int gardenId = this.town.pickRandomPark();
            //out.printf("Garden %d in %s condition\r\n", gardenId, this.town.goodParkCondition(gardenId)? "good" : "bad");

            if (!this.town.goodParkCondition(gardenId)) {
                complaints.add(String.format("El jardín %d estaba en mal estado", gardenId));
            } else {
            }
            try {
                sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
