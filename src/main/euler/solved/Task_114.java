package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

//Answer : 16475640049
public class Task_114 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_114());
        Logger.close();
    }

    final int SZ = 50;
    public void solving() {
        System.out.println( count() );
    }

    private long count() {
        long[] d = new long[SZ+1];
        d[0] = d[1] = 1;
        for (int i = 2; i <= SZ; ++i) {
            d[i] = d[i-1];
            if (i > 2) d[i]++;
            for (int j = 0; j < i-3; ++j) {
                d[i] += d[j];
            }
        }
        return d[SZ];
    }
}
