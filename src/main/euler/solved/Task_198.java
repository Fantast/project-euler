package solved;

import tasks.AbstractTask;
import tasks.Tester;
import utils.MyMath;
import utils.log.Logger;

//Answer : 52374425
// see: https://www.wikiwand.com/en/Farey_sequence
public class Task_198 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_198());
        Logger.close();
    }

    //    public long n = 1000;
//    public long n = 1000000;
    public long n = 100000000;
    public long n2 = n / 2;

//    For 10^3, 2285
//    For 10^5, < 1/100, I got 50271 = (321 + 49950) correct
//    For 10^6, < 1/100, I got 509718 = (9768 + 499950) miss some

    long res = 0;

    public void solving() {
        long res = 0;
        for (long d = 51; d <= n2; ++d) {
            progress1000(d);
            long bmax = n2/d;
            long amax = bmax / 100;
            for (long a = 0; a <= amax; ++a) {
                long bmin = a*100 + 1;
                long cc = a*d + 1;

                for (long div : MyMath.getDivisors(cc)) {
                    if (div >= bmin && div <= bmax) {
                        res++;
                    }
                }
            }
        }
        System.out.println(res);
    }
}
