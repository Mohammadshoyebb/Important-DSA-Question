// Description:
// Given two numbers a and b (inclusive) find the product of primes within this range. Print the product modulo 10^9+7. If there are no primes in that range you must print 1.

// Input Format:
// First Line contains two Space Separated Integers a and b

// Output Format:
// Product Of Primes between a and b

// Constraints:
// 1 ≤ L ≤ R ≤ 10^9
// 0 ≤ L - R ≤ 10^6

// Public Test Cases:
// Test Case 1
// Input:
// 10 50

// Output:
// 563258399

// Explanation:
// Self Explanatory

import java.util.*;

public class ProductsOfPrimes {
    private static final int MOD = 1000000007;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        scanner.close();

        System.out.println(productOfPrimes(a, b));
    }

    private static long productOfPrimes(int a, int b) {
        if (a > b) {
            return 1;
        }

        // Step 1: Find all primes up to sqrt(b) using Sieve of Eratosthenes
        int limit = (int) Math.sqrt(b) + 1;
        boolean[] isPrime = new boolean[limit + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i * i <= limit; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= limit; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= limit; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }

        // Step 2: Use the primes to mark non-prime numbers in the range [a, b]
        boolean[] isPrimeRange = new boolean[b - a + 1];
        Arrays.fill(isPrimeRange, true);

        for (int prime : primes) {
            int start = Math.max(prime * prime, (a + prime - 1) / prime * prime);
            for (int j = start; j <= b; j += prime) {
                isPrimeRange[j - a] = false;
            }
        }

        // Step 3: Calculate the product of primes in the range [a, b]
        long product = 1;
        for (int i = 0; i <= b - a; i++) {
            if (isPrimeRange[i] && (i + a) > 1) {
                product = (product * (i + a)) % MOD;
            }
        }

        return product;
    }
}

