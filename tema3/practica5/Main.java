package practica5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String url;
        ObservableList<String> urls = FXCollections.observableArrayList();
        Downloader downloader = new Downloader();
        urls.addListener(downloader);

        do {
            System.out.println("Introuduce una URL (si pones 'exit' se para el programa): ");
            url = sc.nextLine();

            urls.add(url);
        } while(!url.equalsIgnoreCase("exit"));
    }
}
