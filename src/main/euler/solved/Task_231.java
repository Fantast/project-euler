package solved;

import tasks.ITask;
import tasks.Tester;

//Answer : 7526965179680
public class Task_231 implements ITask {
    final int K = 15000000;
    final int N = 20000000;

    boolean notPrime[] = new boolean[N + 1];
    long ans = 0;

    public void solving() {
        int n = notPrime.length - 1;
        for (long i = 2; i <= n; ++i) {
            if (!notPrime[(int) i]) {
                for (long j = i; j <= n; j += i) {
                    notPrime[(int) j] = true;

                    if (j >= N - K + 1) {
                        long j1 = j;
                        while (j1 % i == 0) {
                            ans += i;
                            j1 /= i;
                        }
                    }

                    if (j <= K) {
                        long j1 = j;
                        while (j1 % i == 0) {
                            ans -= i;
                            j1 /= i;
                        }
                    }
                }
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        Tester.test(new Task_231());
    }
}
