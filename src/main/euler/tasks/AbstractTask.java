package tasks;

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
}
