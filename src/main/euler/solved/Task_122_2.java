package solved;

import tasks.AbstractTask;
import tasks.Tester;
import utils.MyMath;
import utils.log.Logger;

import java.util.HashSet;
import java.util.Set;

//Answer : 1582
public class Task_122_2 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_122_2());
        Logger.close();
    }

    int MAX = 15;

    public void solving() {
        System.out.println(minMults(15));
        System.out.println(minMults(127));
//        long res = 0;
//        for (int k = 1; k <= MAX; ++k) {
//            res += minMults(k);
//        }
//
//        System.out.println( res );
    }

    private int minMults(int k) {
        need = k;
        best = MyMath.bitCount(k) + MyMath.mostSignificantBit(k) - 1;

        Set<Integer> has = new HashSet<>();
        has.add(1);

        find(has, 0);

        return best;
    }

    int best;
    int need;
    void find(Set<Integer> has, int current) {
        if (current >= best) {
            return;
        }

        Set<Integer> has2 = new HashSet<>(has);
        for (int e1 : has) {
            for (int e2 : has) {
                int e = e1 + e2;
                if (e == need) {
                    best = current + 1;
                    return;
                }
                if (!has.contains(e)) {
                    has2.add(e);
                    find(has2, current + 1);
                    has2.remove(e);
                }
            }
        }
    }
}
