package ejemplos;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static java.lang.Thread.sleep;

public class FutureGet {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> fastFuture = CompletableFuture.supplyAsync(() -> {
            return "Hello, I am fast!";
        });
        CompletableFuture<String> slowFuture = CompletableFuture.supplyAsync(() -> {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Hello, I am Internet Explorer!";
        });
        System.out.println(slowFuture.get());
        System.out.println(fastFuture.get());
    }
}
