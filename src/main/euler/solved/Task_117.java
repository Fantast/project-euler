package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

//Answer : 100808458960497
public class Task_117 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_117());
        Logger.close();
    }

    final int SZ = 50;
    public void solving() {
        System.out.println( count() );
    }

    private long count() {
        long[] d = new long[SZ+1];
        // just precalc for some first to avoid out of bounds...
        d[0] = d[1] = 1;
        for (int i = 2; i < 5; ++i) {
            d[i] = 2*d[i-1];
        }

        for (int i = 4; i <= SZ; ++i) {
            d[i] = d[i-1] + d[i-2] + d[i-3] + d[i-4];
        }
        return d[SZ];
    }
}
