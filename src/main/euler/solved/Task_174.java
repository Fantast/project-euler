package solved;

import tasks.ITask;
import tasks.Tester;

import static utils.MyMath.getDivisors;

//Answer : 209566
public class Task_174 implements ITask {
    private long MAX = 1000000;
    public void solving() {
        int res = 0;
        for (long t = 8; t <= MAX; ++t) {
            int L = L(t);
            if (1 <= L && L <= 10) {
                res ++;
            }
        }
        System.out.println(res);
    }

    private int L(long t) {
        int L = 0;
        for (long d1 : getDivisors(t)) {
            long d2 = t / d1;
            if (d2 <= d1) break;

            if (d1%2==0 && d2%2!=0) continue;
            if (d1%2!=0 && d2%2==0) continue;

//            long a = (d1+d2) / 2;
//            long b = (d2-d1) / 2;
//            if ((a-b)%2 != 0) continue;
//            ==> a-b == d1;
            if (d1%2 != 0) continue;

            ++L;
            if (L > 10) return L;
        }
        return L;
    }

    public static void main(String[] args) {
        Tester.test(new Task_174());
    }
}
