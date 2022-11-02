import java.util.Random;

public class Runner {
    private String symbol;
    private int baseSpeed;
    private int turboChance;
    private int fallChance;
    private int position;

    public String getSymbol() {
        return symbol;
    }

    public int getBaseSpeed() {
        return baseSpeed;
    }

    public int getTurboChance() {
        return turboChance;
    }

    public int getFallChance() {
        return fallChance;
    }

    public int getPosition() {
        return position;
    }

    public Runner(String symbol, int baseSpeed, int turboChance, int fallChance) throws Exception {
        this.symbol = symbol;
        this.baseSpeed = baseSpeed;
        this.turboChance = turboChance;
        this.fallChance = fallChance;
        this.position = 0;
        if (!Runner.validAttributes(baseSpeed, turboChance, fallChance)) {
            throw new Exception("Invalid Runner attributes.");
        }
    }

    public synchronized boolean advanceTillEnd(int raceEnd) {
        if (position < raceEnd) {
            if (Math.random() < turboChance / 10.) {
                position += baseSpeed;
            }
            position += baseSpeed;
        }
        return position < raceEnd;
    }

    public synchronized boolean fallTillEnd(int raceEnd) {
        if (position < raceEnd) {
            if (Math.random() < fallChance / 10.) {
                position -= baseSpeed;
            }
        }
        return position < raceEnd;
    }

    static boolean validAttributes(int speed, int turbo, int fall) {
        int sum = speed + turbo + fall;
        return (speed <= 5 &&
                turbo <= 5 &&
                fall <= 5 &&
                speed >= 1 &&
                turbo >= 1 &&
                fall >= 1 &&
                sum <= 10);
    }

    @Override
    public String toString() {
        return this.getSymbol();
    }
}
