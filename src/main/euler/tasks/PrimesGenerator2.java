package tasks;

import java.io.*;

public class PrimesGenerator2 {

    static long maxN = 100000000L;
    static long primes[] = new long[50000000];
    static int primesCount = 0;

    public static void main(String[] args) {
        try (BufferedWriter outFile = new BufferedWriter(new FileWriter("./files/primes.dat"))) {

            primes[primesCount++] = 2;
            outFile.write(2 + "\n");
            for (long n = 3; n <= maxN; n += 2) {
                long maxp = (long) (Math.sqrt(n) + 1);
                boolean isPrime = true;
                for (long p : primes) {
                    if (p > maxp || p == 0) {
                        break;
                    }
                    if (n % p == 0) {
                        isPrime = false;
                        break;
                    }
                }
                if (isPrime) {
                    primes[primesCount++] = n;
                    outFile.write(n + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
