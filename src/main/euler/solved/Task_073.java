package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import static utils.MyMath.gcd;

//Answer : 5066251
public class Task_073 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_073());
        Logger.close();
    }

    public void solving() {
        long count = 10000;
        long res = 0;

        for (int i = 1; i <= count; ++i) {
            res += phi2(i);
        }

        System.out.println(res-1);
    }

    public long phi2(long n) {
        long lim2 = n/2;
        long lim3 = n/3 + 1;

        long res = 0;
        for (long i = lim3; i <= lim2; ++i) {
            if (gcd(i,n) == 1) {
                res++;
            }
        }

        return res;
    }
}
