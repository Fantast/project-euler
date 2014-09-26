package solved;

import tasks.ITask;
import tasks.Tester;

//Answer : 18522
public class Task_126 implements ITask {
    private static final int LIM = 19000;

    int cnt[] = new int[LIM+1];

    public void solving() {
        for (int a = 1; a < LIM; ++a) {
            if (C(a,1,1,1) > LIM) break;
            for (int b = a; b < LIM; ++b) {
                if (C(a,b,1,1) > LIM) break;
                for (int c = b; c < LIM; ++c) {
                    if (C(a,b,c,1) > LIM) break;
                    for (int k = 1; k < LIM; ++k) {
                        int cv = C(a, b, c, k);
                        if (cv > LIM) break;

                        cnt[cv]++;
                    }
                }
            }
        }

        for (int i = 1; i < LIM; ++i) {
            if (cnt[i] == 1000) {
                System.out.println(i);
                break;
            }
        }
    }

    private int C(int a, int b, int c, int i) {
        return 2 * (a * b + a * c + b * c) + 4 * (i - 1) * (a + b + c + i - 2);
    }

    public static void main(String[] args) {
        Tester.test(new Task_126());
    }
}