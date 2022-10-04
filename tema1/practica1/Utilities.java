/**
 * Class with static functions needed for exercicse 1.
 */
public class Utilities {

    /**
     *
     * @param numbers Fills an array with high andom numbers.
     */
    public static void randomNumbersFiller(long[] numbers) {
        long minValue = (long) ((float) Long.MAX_VALUE * 0.90);
        long maxValue = Long.MAX_VALUE;
        for (int i = 0; i < numbers.length; i++)
            numbers[i] = (long) (Math.random() * (maxValue - minValue) + minValue);
    }

    /**
     * Returns true if number is prime, false otherwise.
     * @param number The number to calculate if it is prime or not.
     * @return true if the number is prime, false otherwise.
     */
    public static boolean isPrimeNaive(long number) {
        boolean isPrime = true;

        if (number < 2) isPrime = false;
        else {
            for (long i = 2; i < number && isPrime; i++) {
                if (number % i == 0) {
                    isPrime = false;
                }
            }
        }

        return isPrime;
    }

    /**
     * Indicates which numbers are prime and which ones are not.
     * @param numbers an array of long numbers to calculate if they are prime or not.
     * @param isPrimeNumbers if numbers[i] is prime then isPrimeNumbers[i] will be populated with true value, otherwise false
     */
    public static void arePrimeNaiveCalculator(long[] numbers, boolean[] isPrimeNumbers) {
        if (numbers.length == isPrimeNumbers.length) {
            for (int i = 0; i < numbers.length; i++) {
                isPrimeNumbers[i] = isPrimeNaive(numbers[i]);
            }
        }
    }
}

