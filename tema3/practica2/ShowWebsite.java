package practica2;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class ShowWebsite {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner sc = new Scanner(System.in);
        System.out.print("URL (http://example.com): ");
        String url = sc.nextLine();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        Future<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                return client.send(request,HttpResponse.BodyHandlers.ofString()).body();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).whenComplete((response, error) -> System.out.println(response));

        while (!future.isDone()){
            Thread.sleep(100);
        }
    }
}
