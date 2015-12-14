package solved;

import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static utils.MyMath.bitCount;
import static utils.MyMath.pow;

//Answer : 22472871503401097
public class Task_538 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_538());
        Logger.close();
    }

//    public int LIM = 150;
    public int THRESHOLD = 10;
    public int LIM = 3000000;

    public void solving() {
        long s = 0;

        List<Long> sides = new ArrayList<>();

        for (int n = 1; n <= LIM; ++n) {
            progress100000(n);

            sides.add(U(n));
            Collections.sort(sides);

            if (sides.size() > THRESHOLD) {
                sides.remove(0);
            }

            for (int i = 3; i < sides.size(); ++i) {
                quad(sides.get(i - 3), sides.get(i - 2), sides.get(i - 1), sides.get(i));
            }

            s += bestP;
        }
        System.out.println(s);
    }

    BigInteger bestA = BigInteger.ZERO;
    long bestP = 0;

    private void quad(long a, long b, long c, long d) {
        long P = a + b + c + d;

        long mx = max(a, b, c, d);
        if (P - mx > mx) {
            BigInteger bP = bi(P);
            BigInteger A = bP.subtract(bi(2*a))
                    .multiply(bP.subtract(bi(2*b)))
                    .multiply(bP.subtract(bi(2*c)))
                    .multiply(bP.subtract(bi(2*d)));
//            long A = (P - 2 * a) * (P - 2 * b) * (P - 2 * c) * (P - 2 * d);
            int cmp = A.compareTo(bestA);
            if (cmp > 0) {
                bestA = A;
                bestP = P;
            } else if (cmp == 0 && P > bestP) {
                bestP = P;
            }
        }
    }

    private long U(int n) {
        return pow(2, bitCount(3 * n)) + pow(3, bitCount(2 * n)) + bitCount(n + 1);
    }
}
