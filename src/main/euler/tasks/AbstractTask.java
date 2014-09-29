package tasks;

public abstract class AbstractTask implements ITask {
    
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
}
