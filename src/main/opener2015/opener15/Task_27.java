package opener15;

import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;
import utils.pairs.LongPair;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.abs;

//Answer : 1.[20556943]
//https://cs.uwaterloo.ca/journals/JIS/VOL10/Rittaud2/rittaud11.html
public class Task_27 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_27());
        Logger.close();
    }

    public void solving() {
        Map<LongPair, Long> tmp;
        Map<LongPair, Long> all = new HashMap<>();
        Map<LongPair, Long> all2 = new HashMap<>();

        all.put(new LongPair(0, 1), 1L);

        long prevsum = 1;
        long newsum;
        for (int i = 1; i < 10; ++i) {
            all2.clear();
            newsum = 0;
            for (Map.Entry<LongPair, Long> e : all.entrySet()) {

                LongPair p = e.getKey();
                long c = e.getValue();

                LongPair p1 = new LongPair(p.b, p.a + p.b);
                LongPair p2 = new LongPair(p.b, abs(p.a - p.b));

                accumPut(all2, p1, c);
                accumPut(all2, p2, c);

                newsum += c * (p1.b + p2.b);
            }
            tmp = all;
            all = all2;
            all2 = tmp;

            System.out.println(all);
            System.out.println(newsum);
            System.out.println(i + ": " + all.size() + ":" + 0.5*newsum/prevsum);

            prevsum = newsum;
        }
    }

    private void accumPut(Map<LongPair, Long> all, LongPair p1, long value) {
        Long val = all.get(p1);
        all.put(p1, val == null ? value : val + value);
    }
}
