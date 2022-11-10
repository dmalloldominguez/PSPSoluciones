package ejercicio1;

public class Town {
    private final boolean[] parks;

    public Town(int parks) {
        this.parks = new boolean[parks];
        for (int i = 0; i < parks; i++) {
            this.parks[i] = false;
        }
    }

    public synchronized int workPark() {
        int park;
        do {
            park = (int) Math.floor(Math.random() * (this.parks.length));

        } while (this.parks[park]);
        this.parks[park] = true;
        return park;
    }

    public synchronized void freePark(int i) {
        this.parks[i] = false;
    }
}
