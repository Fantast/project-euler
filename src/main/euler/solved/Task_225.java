package solved;

import tasks.ITask;
import tasks.Tester;
import utils.triples.LongTriple;

import java.util.TreeSet;

//Answer : 2009
public class Task_225 implements ITask {

    public void solving() {
        int cnt = 0;
        for (int n = 3; ; n += 2) {
            if (notDivisible(n)) {
                ++cnt;

//                System.out.println(cnt + ":" + n);
                if (cnt == 124) {
                    System.out.println(n);
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Tester.test(new Task_225());
    }
    TreeSet<LongTriple> rems = new TreeSet<LongTriple>();

    private boolean notDivisible(int n) {
        rems.clear();

        long r1 = 1;
        long r2 = 1;
        long r3 = 1;

        LongTriple t = new LongTriple(r1, r2, r3);
        while (!rems.contains(t)) {
            rems.add(t);

            long r4 = (r1+r2+r3) % n;
            if (r4 == 0) return false;

            r1 = r2;
            r2 = r3;
            r3 = r4;

            t = new LongTriple(r1, r2, r3);
        }

        return true;
    }
}
