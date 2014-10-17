package tasks;

import utils.MyMath;
import utils.log.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static utils.MyMath.getCachedPrimesInternal;

//Answer :
public class Task_224 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_224());
        Logger.close();
    }

    //    int LIM = 75000000;
    int LIM = 170;

    Map<Long, Squares> p = new HashMap<>();

    long[] primes;
    int pc;
    Set<Long> all = new TreeSet<>();

    public void solving() {
//        MyMath.setMaxPrimesToCache(4500000);
        MyMath.setMaxPrimesToCache(45000);

        primes = getCachedPrimesInternal();
        pc = primes.length;

        for (long prime : primes) {
            if (prime % 4 == 1) {
                long d[] = MyMath.decomposePrimeAsTwoSquares(prime);
                p.put(prime, new Squares(min(d[0], d[1]), max(d[0], d[1])));

//                System.out.println(prime + ": " + Arrays.toString(d));
            }
        }

//        System.out.println("start..");
//        for (int w = 2; w < LIM/2; ++w) {
//            progress1000000(w);
//            Map<Long, Integer> factors1 = MyMath.factor(w - 1);
////            Map<Long, Integer> factors2 = MyMath.factor(w + 1);
//            if (factors1 != factors1) {
//                System.out.print("");
//            }
//        }
//        System.out.println("end..");

        generate(0, 2, 1);

        System.out.println(all.size());
    }

    public void generate(int ind, long curr, long n) {
        if (n < LIM && curr < LIM) {
            all.add(n);
        } else {
            return;
        }

        if (curr % 4 != 3) {
            generate(ind, curr, n * curr);
        } else {
            generate(ind, curr, n * curr * curr);
        }

        for (ind = ind + 1; ind < pc; ++ind) {
            curr = primes[ind];
            generate(ind, curr, n);
        }
    }

    public static class Squares {
        long a;
        long b;

        public Squares(long a, long b) {
            this.a = a;
            this.b = b;
        }
    }
}
