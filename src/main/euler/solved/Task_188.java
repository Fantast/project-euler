package solved;

import tasks.ITask;
import tasks.Tester;

import java.util.Map;
import java.util.TreeMap;

//Answer : 95962097
public class Task_188 implements ITask {
    final long MOD = 100000000;
    final long a = 1777;
    final long b = 1855;

    public void solving() {
        TreeMap<Long, Long> rems = new TreeMap<Long, Long>();
        long rem = a;
        for (long p = 0; !rems.containsKey(rem); ) {
            rems.put(rem, ++p);
            rem = (rem*a)%MOD;
        }
        long circleLen = rems.size();

        rems = flipMap(rems);

        long p = 1;
        for (int i = 0; i < b; ++i) {
            long r = rems.get(p);

            p = r % circleLen;
        }

        System.out.println(rems.get(p));
    }

    private TreeMap<Long, Long> flipMap(TreeMap<Long, Long> mp) {
        TreeMap<Long, Long> res = new TreeMap<Long, Long>();
        for (Map.Entry<Long, Long> entry : mp.entrySet()) {
            res.put(entry.getValue(), entry.getKey());
        }
        return res;
    }

    public static void main(String[] args) {
        Tester.test(new Task_188());
    }

}
