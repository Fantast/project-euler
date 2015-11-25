package solved;

import tasks.AbstractTask;
import tasks.Tester;
import utils.MyMath;
import utils.log.Logger;

//Answer : 1582
public class Task_122 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_122());
        Logger.close();
    }

    int MAX = 10000;
    int chain[] = new int[20];

    public void solving() {
        chain[0] = 1;

        long res = 0;
        for (int k = 1; k <= MAX; ++k) {
            res += minMults(k);
        }

        System.out.println( res );
    }

    private int minMults(int k) {
        need = k;
        best = MyMath.bitCount(k) + MyMath.mostSignificantBit(k) - 1;

        find(0, 4);

        return best;
    }

    int best;
    int need;
    void find(int steps, int smallsLeft) {
        if (steps >= best) {
            return;
        }
        int last = chain[steps];
        if (last == need) {
            best = steps;
            return;
        }

        int nextStep = steps + 1;
        if (last * 2 <= need) {
            chain[nextStep] = last * 2;
            find(nextStep, smallsLeft);
        }

        if (smallsLeft > 0) {
            --smallsLeft;
            int nextMin = last + 1;
            for (int i = 0; i < steps; ++i) {
                int a = chain[i];
                int bmin = nextMin - a;
                for (int j = steps; j > i && chain[j] >= bmin; --j) {
                    int next = a + chain[j];
                    if (next <= need) {
                        chain[nextStep] = next;
                        find(nextStep, smallsLeft);
                    }
                }
            }
        }
    }
}
