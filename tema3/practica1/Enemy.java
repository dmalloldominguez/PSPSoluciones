package practica1;

import javafx.collections.ListChangeListener;

public class Enemy extends Coordinates2D implements ListChangeListener<Coordinates2D> {
    public Enemy(int x, int y) {
        super(x, y);
    }

    @Override
    public void onChanged(Change<? extends Coordinates2D> change) {
        while (change.next()) {
            Coordinates2D player = change.getAddedSubList().get(0);
            int distance = this.getDistance(player);
            int movements = 0;
            while (movements < 2) {
                this.deltaX(1);
                if (this.getDistance(player) >= distance) {
                    this.deltaX(-1);
                } else {
                    movements++;
                }

                this.deltaX(-1);
                if (this.getDistance(player) >= distance) {
                    this.deltaX(1);
                } else {
                    movements++;
                }

                this.deltaY(1);
                if (this.getDistance(player) >= distance) {
                    this.deltaY(-1);
                } else {
                    movements++;
                }

                this.deltaY(-1);
                if (this.getDistance(player) >= distance) {
                    this.deltaY(1);
                } else {
                    movements++;
                }
            }
            System.out.println(this);
        }
    }

    @Override
    public String toString() {
        return "ENEMY (" + this.getX() + "," + this.getY() + ")";
    }
}
