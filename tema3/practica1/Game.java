package practica1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Random;
import java.util.Scanner;

public class Game {

    private static Enemy createEnemy(Player player, ObservableList<Coordinates2D> playerMoves) {
        Enemy enemy;
        do {
            enemy = new Enemy(new Random().nextInt(30), new Random().nextInt(30));
        } while (player.getDistance(enemy) < 5);
        playerMoves.addListener(enemy);
        return enemy;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Player player;
        player = new Player(new Random().nextInt(30), new Random().nextInt(30));
        ObservableList<Coordinates2D> playerMoves = FXCollections.observableArrayList();

        Enemy enemy1, enemy2, enemy3;
        enemy1 = createEnemy(player, playerMoves);
        enemy2 = createEnemy(player, playerMoves);
        enemy3 = createEnemy(player, playerMoves);

        System.out.println("Move using A W S D");
        while (player.getDistance(enemy1) != 0 && player.getDistance(enemy2)!=0 && player.getDistance(enemy3)!=0) {

            char movement = Character.toUpperCase(sc.next().charAt(0));
            switch (movement) {
                case 'W' -> {
                    player.deltaY(1);
                    System.out.println(player);
                    playerMoves.add(player);
                }
                case 'D' -> {
                    player.deltaX(1);
                    System.out.println(player);
                    playerMoves.add(player);
                }
                case 'S' -> {
                    player.deltaY(-1);
                    System.out.println(player);
                    playerMoves.add(player);
                }
                case 'A' -> {
                    player.deltaX(-1);
                    System.out.println(player);
                    playerMoves.add(player);
                }
            }
            System.out.println();
        }
        System.out.println("Game over!");
    }
}
