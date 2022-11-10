package ejercicio2;

import static java.lang.System.currentTimeMillis;

public class Town {
    private class Park {
        final int MAXWORKINGTIME = 2000;
        private int id;
        private boolean isBeingWorked;
        private long lastWorked;

        public Park(int id) {
            this.id = id;
            this.isBeingWorked = false;
            this.lastWorked = 0;
        }

        public boolean isBeingWorked() {
            return isBeingWorked;
        }

        public synchronized void work() {
            this.lastWorked = currentTimeMillis();
            this.isBeingWorked = true;
        }

        public synchronized void free() {
            this.isBeingWorked = false;
        }

        public boolean checkCondition() {
            return (currentTimeMillis() - this.lastWorked) < MAXWORKINGTIME;
        }
    }

    private final Park[] gardens;

    public Town(int nGardens) {
        this.gardens = new Park[nGardens];
        for (int i = 0; i < nGardens; i++) {
            this.gardens[i] = new Park(i);
        }
    }

    public int pickRandomPark() {
        return (int) Math.floor(Math.random() * (this.gardens.length));
    }

    public synchronized int workPark() {
        int gardenId;
        do {
            gardenId = (int) Math.floor(Math.random() * (this.gardens.length));

        } while (this.gardens[gardenId].isBeingWorked());
        this.gardens[gardenId].work();
        return gardenId;
    }

    public void freePark(int gardenId) {
        this.gardens[gardenId].free();
    }

    public boolean goodParkCondition(int gardenId) {
        return this.gardens[gardenId].checkCondition();
    }

}
