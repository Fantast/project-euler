package tasks;

import java.io.File;
import java.io.PrintStream;

public class PrimesGenerator2 {

    static long maxN = 100000000L;
    static long primes[] = new long[50000000];
    static int primesCount = 0;

    public static void main(String[] args) {
        try {
            PrintStream outFile = new PrintStream(new File("./files/primes.dat"));
            primes[primesCount++] = 2;
            outFile.println(2);
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
                    outFile.println(n);
                }
            }
            outFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
