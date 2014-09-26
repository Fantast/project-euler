package solved;

import tasks.ITask;
import tasks.Tester;

import java.math.BigInteger;

import static java.math.BigInteger.valueOf;

//Answer : 16576
public class Task_160 implements ITask {
    final long LIM = 1000000000000L;

    private int MOD = 100000;
    private BigInteger MODBI = valueOf(MOD);
    long cnt2 = 0;
    long oddGrMOD[] = new long[MOD];

    public void solving() {
        long curr = 1;
        for (int r = 1; r < MOD; r += 2) {
            if (r % 5 != 0) {
                curr = (curr * r) % MOD;
                oddGrMOD[r] = curr;
            }
        }

        long res = processN(LIM);
        long mod2 = valueOf(2).modPow(valueOf(cnt2), MODBI).longValue();

        System.out.println((res * mod2) % MOD);
    }

    private long processN(long n) {
        if (n < 2) {
            return 1;
        }

        cnt2 += n/2;

        return (processN(n/2) * processOddN(n)) % MOD;
    }

    private long processOddN(long n) {
        if (n < 2) {
            return 1;
        }
        if (n % 2 == 0) {
            --n;
        }

        cnt2 -= (n + 5) / 10;

        return (rMOD(n) * processOddN(n / 5)) % MOD;
    }

    private long rMOD(long n) {
        long oddGrCnt = n / MOD;
        long oddGrVal = oddGrMOD[MOD - 1];
        long res = valueOf(oddGrVal).modPow(valueOf(oddGrCnt), MODBI).longValue();

        int remOdd = (int) (n - oddGrCnt * MOD);
        if (remOdd % 2 == 0) {
            remOdd -= 1;
        }
        if (remOdd % 5 == 0) {
            remOdd -= 2;
        }

        return (res * oddGrMOD[remOdd]) % MOD;
    }

    public static void main(String[] args) {
        Tester.test(new Task_160());
    }
}
