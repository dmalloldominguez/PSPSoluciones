public class AnimalThread extends Thread{
    private String animal;
    private double fallProbability;

    AnimalThread(String animal, int priority, double fallProbability) {
        this.animal = animal;
        this.fallProbability = fallProbability;
        this.setPriority(priority);
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            if (Math.random()<fallProbability) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.printf("%s ha llegado.\r\n", animal);
    }
}
