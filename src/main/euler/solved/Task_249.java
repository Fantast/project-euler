package solved;

import tasks.ITask;
import tasks.Tester;

import java.util.Map;
import java.util.TreeMap;

import static utils.MyMath.getPrimesBetween;
import static utils.MyMath.isPrime;

//Answer : 9275262564250418
public class Task_249 implements ITask {

    public long MOD = 10000000000000000L;
    TreeMap<Long, Long> mp = new TreeMap<Long, Long>();

    public void solving() {
        long ps[] = getPrimesBetween(1, 5000);

        for (int i = 0; i < ps.length; ++i) {
            TreeMap<Long, Long> mp2 = new TreeMap<Long, Long>();
            mp2.put(ps[i], 1L);
            for (Map.Entry<Long, Long> e : mp.entrySet()) {
                long key = e.getKey();
                long val = e.getValue();

                add(mp2, key, val);
                add(mp2, key + ps[i], val);
            }

            mp = mp2;
        }

        long res = 0;
        for (Map.Entry<Long, Long> e : mp.entrySet()) {
            long key = e.getKey();
            long val = e.getValue();

            if (isPrime(key)) {
                res = (res + val) % MOD;
            }
        }
        System.out.println(res);
    }

    public static void main(String[] args) {
        Tester.test(new Task_249());
    }

    private void add(TreeMap<Long, Long> mp, long key, long val) {
        Long v = mp.get(key);

        if (v == null) {
            v = val;
        } else {
            v = (v + val) % MOD;
        }
        mp.put(key, v);
    }
}
