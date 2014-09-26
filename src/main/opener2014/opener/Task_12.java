package opener;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import java.math.BigInteger;
import java.util.Arrays;

import static java.lang.Math.min;

//Answer :
public class Task_12 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_12());
        Logger.close();
    }

    int need = 100;
    int n;
    long cn[] = new long[need + 1];

    public void solving() {
        Arrays.fill(cn, -1);
//        System.out.println(partitions(32767));
        for (n = 1; n < 50; ++n) {
            System.out.println("    " + n + ": " + count2());
        }
    }

    public long count2() {
        if (n == 0) {
            return 1;
        }

        long r = 0;
        for (int g = 1; g <= n; ++g) {
            long dr = f(n, g);
//            System.out.println(g + ": " + dr);

            r += dr;
        }
        return r;
    }


    private long f(int n, int g) {
        if (g <= 1 || g >= n - 1) {
            return 1;
        }

        int d = n - g;
        int e = min(g, d);

        int r = 0;
        for (int c = 1; c <= e; ++c) {
            r += f(d, c);
        }
        return r;
    }

    public BigInteger count(int n) {
        BigInteger v = BigInteger.valueOf(n);
        BigInteger result = BigInteger.valueOf(1 - v.abs().signum());
        BigInteger c = BigInteger.ONE;
        while (c.compareTo(v) <= 0) {
            result = result.add(g(v, c));
            c = c.add(BigInteger.ONE);
        }
        return result;
    }

    private BigInteger g(BigInteger n, BigInteger g) {
        if (g.compareTo(BigInteger.ONE) <= 0 || g.compareTo(n.subtract(BigInteger.ONE)) >= 0) {
            return BigInteger.ONE;
        }
        BigInteger result = BigInteger.ONE;
        BigInteger c = BigInteger.valueOf(2);
        while (c.compareTo(g) <= 0) {
            BigInteger d = n.subtract(g);
            if (c.compareTo(d) <= 0) {
                result = result.add(g(d, c));
            }
            c = c.add(BigInteger.ONE);
        }
        return result;
    }
}
