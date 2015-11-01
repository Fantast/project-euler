package tasks;

import java.math.BigInteger;
import java.util.Random;

public abstract class AbstractTask implements ITask {

    private static Random random = new Random();
    protected long lastTime = -1;

    public void timeStamp() {
        long elapsed = Tester.timeElapsed();
        System.out.println("Working time: " + lastTime + " ms.");

        if (lastTime != -1) {
            System.out.println(" From last timeStamp: : " + (elapsed - lastTime) + " ms.");
        }
        lastTime = elapsed;
    }

    public static boolean progress10(long progress) {
        return progress(progress, 10);
    }

    public static boolean progress100(long progress) {
        return progress(progress, 100);
    }

    public static boolean progress1000(long progress) {
        return progress(progress, 1000);
    }

    public static boolean progress10000(long progress) {
        return progress(progress, 10000);
    }

    public static boolean progress100000(long progress) {
        return progress(progress, 100000);
    }

    public static boolean progress1000000(long progress) {
        return progress(progress, 1000000);
    }

    public static boolean progress10000000(long progress) {
        return progress(progress, 10000000);
    }

    public static boolean progress100000000(long progress) {
        return progress(progress, 100000000);
    }

    public static boolean progress(long progress, long mod) {
        if (progress % mod == 0) {
            progress("Progress: ", progress);
            return true;
        }
        return false;
    }

    public static void progress(long progress) {
        progress("Progress: ", progress);
    }

    public static void progress(String message, long progress) {
        System.out.println(message + progress);
    }

    public static int randomInt(int n) {
        return random.nextInt(n);
    }

    public static boolean randomBoolean() {
        return random.nextBoolean();
    }

    public static void swap(int a[], int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = a[i];
    }

    public static void swap(double a[], int i, int j) {
        double t = a[i];
        a[i] = a[j];
        a[j] = a[i];
    }

    public static BigInteger bi(int n) {
        return BigInteger.valueOf(n);
    }

    public static BigInteger bi(long n) {
        return BigInteger.valueOf(n);
    }

    public static BigInteger bi(String s) {
        return new BigInteger(s);
    }

    public static BigInteger bi(String s, int radix) {
        return new BigInteger(s, radix);
    }

    public static int pi(String s) {
        return Integer.parseInt(s);
    }

    public static int pi(String s, int radix) {
        return Integer.parseInt(s, radix);
    }

    public static long pl(String s) {
        return Long.parseLong(s);
    }

    public static long pl(String s, int radix) {
        return Long.parseLong(s, radix);
    }

    public static int abs(int a) {
        return a >= 0 ? a : -a;
    }

    public static long abs(long a) {
        return a >= 0 ? a : -a;
    }

    public static double abs(double a) {
        return a >= 0 ? a : -a;
    }

    public static int max(int a, int... others) {
        int res = a;
        for (int e : others) {
            res = Math.max(a, e);
        }
        return res;
    }

    public static long max(long a, long... others) {
        long res = a;
        for (long e : others) {
            res = Math.max(a, e);
        }
        return res;
    }

    public static int min(int a, int... others) {
        int res = a;
        for (int e : others) {
            res = Math.min(a, e);
        }
        return res;
    }

    public static long min(long a, long... others) {
        long res = a;
        for (long e : others) {
            res = Math.min(a, e);
        }
        return res;
    }
}
