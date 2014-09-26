package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

//Answer : 15964587728784
public class Task_209 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_209());
        Logger.close();
    }

    int to[] = new int[100];

    public void solving() {
        int n = 1 << 6;
        System.out.println(n);
        for (int i = 0; i < n; ++i) {
            int n1 = 0;
            for (int j = 5; j >= 0; --j) {
                int b = getBit(i, j);
                if (j != 0 && b != 0) {
                    n1 = setBit(n1, j - 1, b);
                }
            }
            int nb = getBit(i, 0) ^ (getBit(i, 1) & getBit(i, 2));
            if (nb != 0) {
                n1 = setBit(n1, 5, nb);
            }

            to[i] = n1;
        }

        long od[][] = new long[100][2];
        long zd[][] = new long[100][2];
        zd[0][0] = 1;

        od[1][0] = 1;
        od[1][1] = 0;
        zd[1][0] = 1;
        zd[1][1] = 1;

        long nd[] = new long[100];
        nd[0] = 1;
        nd[1] = 3;
        for (int i = 2; i < 70; ++i) {
            nd[i] = nd[i - 1] + nd[i - 2];

            od[i][0] = od[i - 1][0] + od[i - 1][1];
            od[i][1] = od[i - 1][0];

            zd[i][0] = zd[i - 1][0] + zd[i - 1][1];
            zd[i][1] = zd[i - 1][0];
        }

        boolean used[] = new boolean[100];
        long res = 1;
        for (int i = 0; i < n; ++i) {
            if (used[i]) {
                continue;
            }

            int curr = i;
            int len = 0;
            while (!used[curr]) {
                ++len;
                used[curr] = true;
                curr = to[curr];
            }
            //15964587728784
            //res *= ( zd[len-1][0] + zd[len-1][1] + od[len-1][0]);

            res *= nd[len - 1];
        }
        System.out.println(res);

    }

    private static int setBit(int n, int i, int b) {
        return n | (b << i);
    }

    private static int getBit(int n, int i) {
        return (n >> i) & 1;
    }
}
