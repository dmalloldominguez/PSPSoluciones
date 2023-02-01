package ejemplos;

import java.util.concurrent.CompletableFuture;

import static java.lang.Thread.sleep;

public class FutureExecutionOrder {
    private static String fastMethod() {

        return "2";
    }

    private static String slowMethod() {
        try {
            sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "2";
    }

    public static void main(String[] args) {
        System.out.println("Future with fast method:");
        System.out.println("1");
        CompletableFuture.supplyAsync(() -> fastMethod())
                .thenAccept(System.out::println)
                .whenComplete((nothing, error) -> System.out.println("3"));
        System.out.println("4");

        System.out.println("Future with slow method:");
        System.out.println("1");
        CompletableFuture.supplyAsync(() -> slowMethod())
                .thenAccept(System.out::println)
                .whenComplete((nothing, error) -> System.out.println("3"));
        System.out.println("4");
        while (true);
    }

}
