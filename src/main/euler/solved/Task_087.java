package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import static utils.MyMath.getPrimesBetween;

//Answer : 1097343
public class Task_087 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_087());
        Logger.close();
    }

    private static final int MAX = 50000000;
    boolean can[] = new boolean[MAX+1];

    public void solving() {
        long res = 0;
        long p[] = getPrimesBetween(0, (long) Math.sqrt(MAX)+5);
        int n = p.length;

        for (long pi : p) {
            long p1 = (long) Math.pow(pi, 4);
            for (long pj : p) {
                long p2 = (long) Math.pow(pj, 3);
                if (p1 + p2 > MAX) {
                    break;
                }

                for (long pk : p) {
                    long p3 = (long) Math.pow(pk, 2);
                    int num = (int) (p1 + p2 + p3);
                    if (num > MAX) {
                        break;
                    }

                    if (!can[num]) {
                        ++res;
                        can[num] = true;
                    }
                }
            }
        }

        System.out.println( res );
    }
}
