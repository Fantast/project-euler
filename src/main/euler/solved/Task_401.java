package solved;

import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;

import java.util.Arrays;

//Answer : 281632621
public class Task_401 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_401());
        Logger.close();
    }


    public long LIM = 1000000000000000L;
    public int sqLim = (int) Math.sqrt(LIM);
    public long MOD = 1000000000L;

    public void solving() {
        System.out.println(sqLim);
        System.out.println("Generating...");

        long divs[] = new long[sqLim * 2];
        int divCount = 0;
        for (long i = 1; i <= sqLim; ++i) {
            divs[divCount++] = i;
            divs[divCount++] = LIM / i;
        }

        System.out.println("Sorting...");
        Arrays.sort(divs);

        System.out.println("Counting...");

        long res = LIM % MOD; // LIM ones in the sum
        for (int i = 1; i < divs.length; ++i) {
            progress1000000(i);

            long div = divs[i];

            long count = LIM / div % MOD;
            if (i < sqLim) {
                div = div * div % MOD;
                res = (res + count * div) % MOD;
            } else {
                res = (res + count * sumOfSqaures(divs[i - 1] + 1, divs[i]) % MOD) % MOD;
            }
        }
        System.out.println(res);
    }

    public long sumOfSqaures(long a, long b) {
        long smb = sumOfSqaures(b);
        long sma = sumOfSqaures(a - 1);
        return (smb + MOD - sma) % MOD;
    }

    public long sumOfSqaures(long n) {
        long n2 = n + 1;
        long n3 = 2 * n + 1;
        if (n % 2 == 0) {
            n /= 2;
        } else {
            n2 /= 2;
        }

        if (n % 3 == 0) {
            n /= 3;
        } else if (n2 % 3 == 0) {
            n2 /= 3;
        } else {
            n3 /= 3;
        }

        n %= MOD;
        n2 %= MOD;
        n3 %= MOD;

        return (n * n2) % MOD * n3 % MOD;
    }
//    public BigInteger sumOfSqaures(long n) {
//        BigInteger N = valueOf(n);
//        BigInteger res = N.pow(3).multiply(valueOf(2));
//        res = res.add(N.pow(2).multiply(valueOf(3)));
//        res = res .add(N);
//        res = res.divide(valueOf(6));
//        return res;
//    }
}
