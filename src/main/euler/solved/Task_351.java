package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

//Answer : 11762187201804552
public class Task_351 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_351());
        Logger.close();
    }

    long H = 100000000;

    public void simpleSolvingAfterReadingForum() {
//    public void solving() {
        countPhis();

        long res = H*(H-1)/2;

        System.out.println("Computing result...");
        for (int r = 1; r <= H; ++r) {
            res -= phi[r];
        }
        System.out.println(6 * res);
    }

    public void solving() {
        countPhis();

        long res = 0;

        System.out.println("Computing result...");
        for (int r = 2; r <= H; ++r) {
            long rr = 0;
            if (r % 2 == 0) {
                int rn = r / 2;

                if (rn % 2 != 0) {
                    rr = 1 + rn - phi2[rn];
                    if (rn == 1) {
                        --rr;
                    }
                } else {
                    rr = 1 + rn - phi[rn];
                }
            } else {
                rr += r - phi[r] - r / 2 + phi2[r];
            }
            res += rr;
        }
        System.out.println(6 * (res * 2 - 3 * H / 2 + 2));
    }

    final int MAX = (int) (H + 1);
    int phi[] = new int[MAX];
    int phi2[] = new int[MAX];
    boolean notPrime[] = new boolean[MAX];

    private void countPhis() {
        for (int n = 1; n < MAX; ++n) {
            phi[n] = n;
            phi2[n] = n/2;
        }

        System.out.println("Computing phis...");
        for (int n = 2; n < MAX; ++n) {
            if (!notPrime[n]) {
                phi[n]--;
                for (int i = n + n; i < MAX; i += n) {
                    notPrime[i] = true;
                    phi[i] = phi[i] - phi[i] / n;
                    phi2[i] = phi2[i] - phi2[i] / n;
                }
            }
        }
    }
}
