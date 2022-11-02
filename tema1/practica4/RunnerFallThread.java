public class RunnerFallThread extends Thread{
    private Runner runner;
    private int raceEnd;

    public RunnerFallThread(Runner runner, int raceEnd) {
        this.runner = runner;
        this.raceEnd = raceEnd;
    }

    @Override
    public void run() {
        while (runner.fallTillEnd(raceEnd)) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
