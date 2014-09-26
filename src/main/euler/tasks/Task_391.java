package tasks;

import utils.MyMath;
import utils.log.Logger;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import static utils.MyMath.bitCount;

//Answer :
public class Task_391 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_391());
        Logger.close();
    }

    int LIM = 20;
//    int LIM = 20;

    int n;

    public void solving() {
        long res = 0;
        for (n = 20; n <= LIM; ++n) {
            long m = M();
            res += m*m*m;
        }

        System.out.println(res);
    }

    private long M() {
        cache.clear();

        long res = 0;
        long ssum = 1;
        long snum = 1;
        for (int k = 1; k <= n; ++k) {
            if (k == ssum) {
                if (!isWinning(snum, ssum)) {
                    res = k;
                }
            }

            while (k >= snum) {
                ++snum;
                ssum += bitCount(snum);
            }
        }
        return res;
    }

    Map<Long, Boolean> cache = new HashMap<Long, Boolean>();
    private boolean isWinning(long snum, long ssum) {
        Boolean win = cache.get(ssum);
        if (win == null) {
            long next = ssum + bitCount(++snum);
            for (int k = 1; k <= n; ++k) {
                if (ssum + k == next) {
                    if (!isWinning(snum, next)) {
                        win = true;
                        break;
                    }
                }

                while (ssum + k >= next) {
                    ++snum;
                    next += bitCount(snum);
                }
            }

            if (win == null) {
                win = false;
            }

            cache.put(ssum, win);
        }

        return win;
    }
}
