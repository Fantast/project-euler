package tasks;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static utils.MyMath.doEratosthen;


public class PrimesGenerator {
    static boolean isPrime[] = new boolean[100000000];
    static long primes[] = new long[5800000];
    static int primesCount = 0;

    public static void main(String[] args) {
        primesCount = doEratosthen(primes, isPrime);
        try (BufferedWriter outFile = new BufferedWriter(new FileWriter("./files/primes.dat"))) {
            for (int i = 0; i < primesCount; ++i) {
                outFile.write(primes[i] + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
