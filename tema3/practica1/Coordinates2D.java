package practica1;

public class Coordinates2D {
    private int x;
    private int y;

    public Coordinates2D(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void deltaX(int x) {
        this.x += x;
    }

    public void deltaY(int y) {
        this.y += y;
    }

    public int getDistance(Coordinates2D position) {
        return Math.abs(position.x - x) + Math.abs(position.y-y);
    }

}
