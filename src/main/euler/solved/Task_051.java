package solved;

import tasks.ITask;

import java.util.Arrays;

import static utils.MyMath.getPrimesBetween;
import static utils.MyMath.isPrime;

//Answer : 121313
public class Task_051 implements ITask {
    public static int NEED_PRIMES = -1;
    public static int DIG_COUNT = -1;

    public void solving() {
        long prs[] = getPrimesBetween(100000, 999999);
        DIG_COUNT = 6;
        NEED_PRIMES = 8;

        int digCounts[] = new int[10];
        TOP:
        for (long p : prs) {
            getDigits(p, digCounts);
            int firstDigit = getDigit(p, DIG_COUNT-1);
            for (int oldDig = 0; oldDig <= 9; ++oldDig) {
                if (digCounts[oldDig] != 0) {
                    int primesCount = 1;
                    for (int newDig = 0; newDig <= 9; ++newDig) {
                        if (newDig==oldDig || (newDig == 0 && firstDigit==oldDig)) continue;
                        long n = replaceAllDigits(p, oldDig, newDig);
                        if (isPrime(n)) primesCount++;
                    }
                    if (primesCount == NEED_PRIMES) {
                        System.out.println("Found: " + p);
                        break TOP;
                    }
                }
            }
        }
    }

    private long replaceAllDigits(long n, int oldDig, int newDig) {
        long res = n;
        for (int i = 0; i < DIG_COUNT; ++i) {
            if (getDigit(n, i) == oldDig) {
                res += (newDig - oldDig) * tens[i];
            }
        }
        return res;
    }

    int tens[] = {
            1,
            10,
            100,
            1000,
            10000,
            100000,
            1000000,
            10000000,
            100000000,
            1000000000
    };

    int getDigit(long n, int digIndex) {
        return (int) ((n/tens[digIndex]) % 10);
    }

    private void getDigits(long p, int[] digCounts) {
        Arrays.fill(digCounts, 0);
        while (p!=0) {
            digCounts[ ((int) (p%10)) ]++;
            p /= 10;
        }
    }
}
