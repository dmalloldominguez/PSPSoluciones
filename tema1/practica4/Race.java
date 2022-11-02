import java.util.ArrayList;
import java.util.List;

public class Race{
    private List<Runner> runners;
    private List<RunnerThread> runThreads;
    private List<RunnerFallThread> fallThreads;
    private ThreadSafeList raceResult;
    private RaceUIThread raceUI;
    private static final int raceEnd = 50;

    public Race(List<Runner> runners) {
        this.runners = runners;
        this.raceResult = new ThreadSafeList();
        this.runThreads = new ArrayList<>();
        this.fallThreads = new ArrayList<>();
        for (Runner runner: runners) {
            this.runThreads.add(new RunnerThread(runner, Race.raceEnd, raceResult));
            this.fallThreads.add(new RunnerFallThread(runner, Race.raceEnd));
        }
        this.raceUI = new RaceUIThread(this, raceResult, runners.size());
    }

    public void start() {
        for (RunnerThread runThread: runThreads) {
            runThread.start();
        }
        for (RunnerFallThread fallThread: fallThreads) {
            fallThread.start();
        }
        raceUI.start();
    }

    public void end() throws InterruptedException {
        for (RunnerThread runThread: runThreads) {
            runThread.join();
        }
        for (RunnerFallThread fallThread: fallThreads) {
            fallThread.join();
        }
        raceUI.join();

        System.out.printf("Classification:\r\n");
        for (int i = 0; i < raceResult.size(); i++) {
            System.out.printf("%d - %s\r\n", i+1, raceResult.get(i));
        }
    }

    @Override
    public String toString() {
        String ui = "\r\n".repeat(10) + "*".repeat(52) + "\r\n";
        for (Runner runner: runners) {
            ui += "*" + " ".repeat(runner.getPosition()) + runner.getSymbol() + " ".repeat(Math.max(raceEnd - runner.getPosition()-1, 0)) + "*\r\n";
        }
        ui += "*".repeat(52) + "\r\n";
        return ui;
    }
}
