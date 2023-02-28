package practica5;
import javafx.collections.ListChangeListener;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Downloader implements ListChangeListener<String> {
    @Override
    public void onChanged(Change<? extends String> change) {
        while (change.next()) {
            if (change.wasAdded()) {
                if (!change.getAddedSubList().get(0).equalsIgnoreCase("exit")) {
                    //System.out.println("Se ha iniciado la descarga del archivo " + change.getAddedSubList().get(0) + ".");
                    HttpClient client = HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(change.getAddedSubList().get(0)))
                            .GET()
                            .build();

                    Future<String> future = CompletableFuture.supplyAsync(() -> {
                        try {
                            return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }).whenComplete((response, error) -> {
                        Path path = Paths.get("index.html");
                        try {
                            Files.write(path, response.getBytes(StandardCharsets.UTF_8));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });

                }
            }
        }
    }
}


