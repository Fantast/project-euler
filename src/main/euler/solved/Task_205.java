package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

//Answer : 0.5731441
public class Task_205 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_205());
        Logger.close();
    }

    public void solving() {
        long ps[] = new long[37];
        long cs[] = new long[37];

        fill(ps, 9, 4);
        fill(cs, 6, 6);

        long pwin = 0;
        long call = 0;
        long pall = 0;
        for (int i = 0; i < 37; ++i) {
            pwin += ps[i] * call;
            call += cs[i];
            pall += ps[i];
        }

        System.out.println(pwin / (double) (call * pall));
    }

    private void fill(long[] s, long cnt, long mx) {
        s[0] = 1;
        for (long st = 0; st < cnt; ++st) {
            long s2[] = new long[37];

            for (int j = 1; j <= mx; ++j) {
                for (int i = 0; i <= 36-j; ++i) {
                    s2[i + j] = s2[i + j] + s[i];
                }
            }
            System.arraycopy(s2, 0, s, 0, 37);
        }
    }
}