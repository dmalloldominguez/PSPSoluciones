/**
 * Main program, generates random numbers, tells if they are prime or not and shows execution time.
 */
public class Main {
    public static void main(String[] args) {
        int size = 100;
        long[] numbers = new long[size];
        boolean[] isPrimeNumbers = new boolean[size];
        long startTime, endTime, time;

        Utilities.randomNumbersFiller(numbers);
        startTime = System.nanoTime();
        Utilities.arePrimeNaiveCalculator(numbers, isPrimeNumbers);
        endTime = System.nanoTime();
        time = endTime - startTime;

        for (int i = 0; i < numbers.length; i++) {
            System.out.printf("The number %d is%s prime\n", numbers[i], isPrimeNumbers[i] ? "" : " not");
        }

        System.out.printf("\n%s\nEXECUTION TIME\n", "*".repeat(20));
        System.out.printf("\tNanoseconds:\t\t%dns\n", time);
        System.out.printf("\tMilliseconds:\t\t%dms\n", time / (1000000));
        System.out.printf("\tSeconds:\t\t\t%ds\n", time / (1000000000));
        System.out.printf("%s\n", "*".repeat(20));
    }
}
