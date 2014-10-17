package tasks;

import utils.MyMath;
import utils.log.Logger;

import java.util.HashMap;
import java.util.Map;

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

    int LIM = 75000000;

    Map<Long, Squares> p = new HashMap<>();

    public void solving() {
        MyMath.setMaxPrimesToCache(4500000);

        for (long prime : getCachedPrimesInternal()) {
            if (prime % 4 == 1) {
                long d[] = MyMath.decomposePrimeAsTwoSquares(prime);
                p.put(prime, new Squares(min(d[0], d[1]), max(d[0], d[1])));

//                System.out.println(prime + ": " + Arrays.toString(d));
            }
        }

        System.out.println("start..");
        for (int w = 2; w < LIM/2; ++w) {
            progress1000000(w);
            Map<Long, Integer> factors1 = MyMath.factor(w - 1);
            Map<Long, Integer> factors2 = MyMath.factor(w + 1);
            if (factors1 == factors2) {
                System.out.print("");
            }
        }
        System.out.println("end..");
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
