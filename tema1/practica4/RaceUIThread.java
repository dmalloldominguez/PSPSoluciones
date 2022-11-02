import java.util.List;

public class RaceUIThread extends Thread{
    private Race race;
    private ThreadSafeList result;
    private int runners;

    public RaceUIThread(Race race, ThreadSafeList result, int runners) {
        this.race = race;
        this.result = result;
        this.runners = runners;
    }

    @Override
    public void run() {
        while (result.size() < runners) {
            try {
                System.out.printf(race.toString());

                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
