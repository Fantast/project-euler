package solved;

import tasks.ITask;
import tasks.Tester;
import utils.MyMath;
import utils.log.Logger;
import utils.pairs.LongPair;

import java.util.HashMap;
import java.util.Map;

//Answer : 11109800204052
public class Task_347 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_347());
        Logger.close();
    }

    int LIM = 10000000;
//    int LIM = 100;
    int primeFactorsCnt[] = new int[LIM+1];
    long primeFactors[][] = new long[LIM+1][2];

    public void solving() {
        long[] primes = MyMath.getPrimesBetween(0, LIM / 2);
        for (long p : primes) {
            long next = p;
            while (next <= LIM) {
                int ind = (int) next;
                int cnt = primeFactorsCnt[ind];
                if (cnt < 2) {
                    primeFactors[ind][cnt] = p;
                }

                primeFactorsCnt[ind]++;
                next += p;
            }
        }

        long res = 0;
        Map<LongPair, Integer> distincts = new HashMap<LongPair, Integer>();
        for (int n = LIM; n > 0; --n) {
            if (primeFactorsCnt[n] == 2) {
                LongPair key = new LongPair(primeFactors[n][0], primeFactors[n][1]);
                if (!distincts.containsKey(key)) {
                    distincts.put(key, n);
                    res += n;
                }
            }
        }

        System.out.println(res);
    }
}
