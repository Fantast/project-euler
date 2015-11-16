package solved;

import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;

import java.util.TreeSet;

import static utils.MyMath.getDivisors;
import static utils.MyMath.isPrime;

//Answer : 23507044290
public class Task_248 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_248());
        Logger.close();
    }

    long phi = 6227020800L;

    long possibleFactors[] = new long[500];
    int possCount = 0;

    long factors[] = new long[500];
    int factCount = 0;

    int factorsCombs = 0;

    TreeSet<Long> all = new TreeSet<>();

    public void solving() {
        for (long div : getDivisors(phi)) {
            if (isPrime(div + 1)) {
                possibleFactors[possCount++] = div;
            }
        }
        System.out.println("Go");

        find(phi, 0);

        Long[] ns = all.toArray(new Long[all.size()]);
        System.out.println(ns[0]);
        System.out.println(ns[149999]);
    }

    private void find(long phi, int ind) {
        if (ind == possCount) {
            ++factorsCombs;

            progress10000(factorsCombs); //out of 898876

            register(phi);
            return;
        }

        long factor = possibleFactors[ind];
        if (phi % factor == 0) {
            factors[factCount++] = factor;
            find(phi / factor, ind + 1);
            factCount--;
        }

        find(phi, ind + 1);
    }

    private void register(long phi) {
        long n = 1;

        for (int i = 0; i < factCount; ++i) {
            long prime = factors[i] + 1;
            n *= prime;
            while (phi % prime == 0) {
                n *= prime;
                phi /= prime;
            }
        }

        if (phi == 1) {
            all.add(n);
        }
    }
}
