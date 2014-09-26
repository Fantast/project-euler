package solved;

import tasks.ITask;
import tasks.Tester;

//Answer : 1677366278943
public class Task_214 implements ITask {

    final int LEN = 25;
    final int MAX = 40000000;
    int chainLen[] = new int[MAX];
    int phi[] = new int[MAX];
    boolean notPrime[] = new boolean[MAX];

    public void solving() {
        for (int n = 2; n < MAX; ++n) {
            if (!notPrime[n]) {
                phi[n] = n - 1;
                for (int i = n + n; i < MAX; i += n) {
                    notPrime[i] = true;

                    if (phi[i] == 0) {
                        phi[i] = i;
                    }

                    phi[i] = phi[i] - phi[i] / n;
                }
            }
        }

        long res = 0;
        chainLen[1] = 1;
        for (int i = 2; i < MAX; ++i) {
            chainLen[i] = chainLen[phi[i]] + 1;

            if (chainLen[i] == LEN && !notPrime[i]) {
                res += i;
            }
        }

        System.out.println(res);
    }

    public static void main(String[] args) {
        Tester.test(new Task_214());
    }
}
