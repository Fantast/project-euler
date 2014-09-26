package solved;

import tasks.ITask;
import tasks.Tester;

//Answer : 11325263
public class Task_229 implements ITask {
    int MAX = 10000000;
    int LIM = 3163;

//    int MAX = 2000000000;
//    int LIM = 44721;

    int LEN = MAX / 64;
    long p1[] = new long[LEN];
    long p2[] = new long[LEN];
    long p3[] = new long[LEN];
    long p7[] = new long[LEN];

    public void solving() {
        System.out.println("start");
        for (int a = 1; a < LIM; ++a) {
            if (a % 100 == 0) {
                System.out.println("A: " + a);
            }
            long a2 = a * a;
            for (int b = a; b < LIM; ++b) {
                long b2 = b * b;

                if (a2 + b2 > MAX) {
                    break;
                }
                set(p1, a2 + b2);
                set(p2, a2 + 2 * b2);
                set(p3, a2 + 3 * b2);
                set(p7, a2 + 7 * b2);
            }
        }

        int cnt = 0;
        for (int i = 2; i <= MAX; ++i) {
            if (isset(p1, i) &&
                    isset(p2, i) &&
                    isset(p3, i) &&
                    isset(p7, i)) {
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    public static void main(String[] args) {
        Tester.test(new Task_229());
    }

    private void set(long p[], long i_l) {
        if (i_l > MAX) {
            return;
        }

        int i = (int)(i_l - 1);

        int ind = i / 64;
        long bit = i % 64;

        try {
            p[ind] = p[ind] | (1L << bit);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(i);
            throw e;
        }
    }

    private boolean isset(long[] p, int i) {
        --i;
        int ind = i / 64;
        long bit = i % 64;

        return (p[ind] & (1L << bit)) != 0;
    }
}
