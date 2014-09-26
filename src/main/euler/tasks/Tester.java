package tasks;

public class Tester {
    private static long begTime;

    public static void test(ITask task) {
        begTime = System.nanoTime();

        try {
            task.solving();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        System.out.println("\nTask completed in: " + timeElapsed() + " ms.");
    }

    public static void timeStamp() {
        System.out.println("\nWorking time: " + timeElapsed() + " ms.");
    }

    public static long timeElapsed() {
        return (System.nanoTime() - begTime)/1000000;
    }

    public static void test(Class<? extends ITask> taskClass, int times) {
        System.out.println("Running " + taskClass.getSimpleName() + ".");
        long begTime = System.nanoTime();

        try {
            ITask task = taskClass.getConstructor().newInstance();
            for (int i = 0; i < times; ++i) {
                task.solving();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        long time = System.nanoTime() - begTime;
        System.out.println("Working time: " + time/1000 + " ms.");
    }
}
