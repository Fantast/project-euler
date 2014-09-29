package solved;

import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;

import java.util.ArrayList;
import java.util.TreeSet;

//Answer: 1884161251122450
public class Task_221 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_221());
        Logger.close();
    }

    long LIM = 2100000000000000L;
    long maxr = 80000;
    TreeSet<Long> all = new TreeSet<>();

    public void solving() {

        long p, q, A;

        for (long r = 1; r < maxr; ++r) {
            if (progress10000((int) r)) {
                System.out.println("   " + all.size());
            }
            long limDivR = LIM / r;
            long qMinusR = 1;
            long qrPlus1 = (r + 1) * r + 1;
            long maxQMinusR = r + 1;
            for (; qMinusR < maxQMinusR; ++qMinusR, qrPlus1 += r) {
                if (qrPlus1 % qMinusR == 0) {
                    p = qrPlus1 / qMinusR;
                    q = qMinusR + r;
                    if (p < limDivR / q) {
                        A = p * q * r;
                        all.add(A);
                    }
                }
            }
        }
        System.out.println(new ArrayList<>(all).get(149999));
    }
}
