public class RunnerThread extends Thread{
    private Runner runner;
    private int raceEnd;
    private ThreadSafeList raceResult;

    public RunnerThread(Runner runner, int raceEnd, ThreadSafeList raceResult) {
        this.runner = runner;
        this.raceEnd = raceEnd;
        this.raceResult = raceResult;
    }

    @Override
    public void run() {
        while (runner.advanceTillEnd(raceEnd)) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        raceResult.add(runner.getSymbol());
    }

}
