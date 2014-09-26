package solved;

import tasks.ITask;
import tasks.Tester;

//Answer : 2544559
public class Task_136 implements ITask {

    int LIM = 50000000;
    long cnt[] = new long[LIM];

    public void solving() {
        for (int y = 1; y < LIM; ++y) {
            int mxd = (y+LIM)/4+1;
            for (int d = y/4; d < mxd; ++d) {
                long n = y*(4*d-y);
                if (n >= LIM) break;
                if (n>0 && y>d) {
                    cnt[(int) n]++;
                }
            }
        }

        int res = 0;
        for (int i = 1; i < LIM; ++i) {
            if (cnt[i] == 1) {
                ++res;
            }
        }

        System.out.println(res);
    }

    public static void main(String[] args) {
        Tester.test(new Task_136());
    }
}
