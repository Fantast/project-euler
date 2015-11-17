package solved;

import tasks.AbstractTask;
import tasks.Tester;
import utils.MyMath;
import utils.log.Logger;

//Answer : 98792821
public class Task_429 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_429());
        Logger.close();
    }

    long factors[];
    int factPows[];
    long rems[];
    int factCount = 0;

//    long LIM = 4;
    long LIM = 100000000;

    long MOD = 1000000009L;

    public void solving() {
        MyMath.setMaxPrimesToCache((int) LIM);
        factors = MyMath.getPrimesBetween(0, LIM);
        factCount = factors.length;
        factPows = new int[factCount];
        rems = new long[factCount];

        for (int i = 0; i < factCount; ++i) {
            long fact = factors[i];
            long fPowed = fact;

            int pow = 0;
            while (fPowed <= LIM) {
                pow += LIM /fPowed;
                fPowed *= fact;
            }
            factPows[i] = pow;
            rems[i] = MyMath.modPow(fact, 2*pow, MOD);
        }

        long s = 1;
        for (int i = 0; i < factCount; ++i) {
            s = (s + s*rems[i]) % MOD;
        }

        System.out.println(s);
    }
}
