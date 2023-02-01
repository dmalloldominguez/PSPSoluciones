package ejemplos;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class FutureChained {
    public static void main(String[] args) {
        CompletableFuture<String> chainedFutures
                = CompletableFuture.supplyAsync(() -> "Hello")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> (s + " World").toUpperCase()))
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> (s + " and ")))
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> {return s + "HAVE a nice day".toUpperCase();}));

        try {
            System.out.println(chainedFutures.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}