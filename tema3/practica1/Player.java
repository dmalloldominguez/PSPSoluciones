package practica1;

public class Player extends Coordinates2D{
    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public String toString() {
        return "PLAYER (" + this.getX() + "," + this.getY() + ")";
    }

}
