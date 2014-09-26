package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

//Answer : 16475640049
public class Task_115 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_115());
        Logger.close();
    }

    public void solving() {
        for (int i = 50; ; ++i) {
            if (count(50, i) > 1000000) {
                System.out.println(i);
                break;
            }
        }
    }

    private long count(int m, int n) {
        long[] d = new long[n+1];
        d[0] = d[1] = 1;
        for (int i = 2; i <= n; ++i) {
            d[i] = d[i-1];
            if (i >= m) d[i]++;
            for (int j = 0; j < i-m; ++j) {
                d[i] += d[j];
            }
        }
        return d[n];
    }
}
