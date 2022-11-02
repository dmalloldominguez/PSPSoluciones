import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RaceMain {
    public static void main(String[] args) throws InterruptedException {

        List<Runner> runnerList = new ArrayList<>();;
        RaceMain.scanRunners(runnerList);
        Race race = new Race(runnerList);
        race.start();
        race.end();
    }

    public static void scanRunners(List<Runner> runnerList) {
        System.out.print("Number of runners: ");
        Scanner scanner = new Scanner(System.in);
        int runners = scanner.nextInt();

        while (runnerList.size() < runners) {
            String runnerSymbol;
            int runnerSpeed, runnerTurbo, runnerFall;

            System.out.printf("Runner %d symbol: ", runnerList.size() + 1);
            runnerSymbol = scanner.next();
            System.out.printf("Runner Attributes (Max 10 points)\r\nRunner %d base speed (1-5): ", runnerList.size() + 1);
            runnerSpeed = scanner.nextInt();
            System.out.printf("Runner %d turbo probability (1-5): ", runnerList.size() + 1);
            runnerTurbo = scanner.nextInt();
            System.out.printf("Runner %d fall probability (1-5): ", runnerList.size() + 1);
            runnerFall = scanner.nextInt();

            try {
                runnerList.add(new Runner(runnerSymbol, runnerSpeed, runnerTurbo, runnerFall));
                System.out.printf("Runner %d created\r\n", runnerList.size());
            } catch (Exception e) {
                System.out.printf("Runner not created. Currently %d runners.\r\n", runnerList.size());
            }

        }
    }
}
