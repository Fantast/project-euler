package solved;

import tasks.ITask;
import tasks.Tester;
import utils.Fraction;
import utils.LongFraction;

import java.util.HashSet;

import static java.lang.Math.sqrt;
import static utils.MyMath.isExactSquare;

//Answer : 285196020571078987
public class Task_180 implements ITask {

    private static final int n = 35;
    private HashSet<LongFraction> all = new HashSet<LongFraction>();

    public void solving() {
        for (int b1 = 2; b1 <= n; ++b1) {
            for (int a1 = 1; a1 < b1; ++a1) {
                for (int b2 = 2; b2 <= n; ++b2) {
                    for (int a2 = 1; a2 < b2; ++a2) {
                        check1(a1, b1, a2, b2);
                        check2(a1, b1, a2, b2);
                        checkMinus1(a1, b1, a2, b2);
                        checkMinus2(a1, b1, a2, b2);
                    }
                }
            }
        }

        Fraction sum = Fraction.ZERO;
        for (LongFraction s : all) {
            sum = sum.add( new Fraction(s.numer, s.denom) );
        }
        System.out.println(sum);
        System.out.println(sum.denom.add( sum.numer ) );
    }

    private void check1(int a1, int b1, int a2, int b2) {
        LongFraction x = new LongFraction(a1, b1);
        LongFraction y = new LongFraction(a2, b2);
        LongFraction z = x.add(y);
        add(x, y, z);
    }

    private void check2(int a1, int b1, int a2, int b2) {
        LongFraction x = new LongFraction(a1, b1);
        LongFraction y = new LongFraction(a2, b2);

        LongFraction z2 = x.multiply(x).add( y.multiply(y) );

        if (!isExactSquare(z2.numer) || !isExactSquare(z2.denom)) {
            return;
        }

        LongFraction z = new LongFraction((long)sqrt(z2.numer), (long)sqrt(z2.denom));

        add(x, y, z);
    }

    private void checkMinus1(int a1, int b1, int a2, int b2) {
        LongFraction x = new LongFraction(a1, b1);
        LongFraction y = new LongFraction(a2, b2);
        LongFraction z = x.multiply(y).divide( x.add(y) );
        add(x, y, z);
    }

    private void checkMinus2(int a1, int b1, int a2, int b2) {
        LongFraction x = new LongFraction(a1, b1);
        LongFraction y = new LongFraction(a2, b2);

        LongFraction x2 = x.multiply(x);
        LongFraction y2 = y.multiply(y);

        LongFraction z2 = x2.multiply(y2).divide( x2.add(y2)  );

        if (!isExactSquare(z2.numer) || !isExactSquare(z2.denom)) {
            return;
        }

        LongFraction z = new LongFraction((long)sqrt(z2.numer), (long)sqrt(z2.denom));

        add(x, y, z);
    }

    private void add(LongFraction x, LongFraction y, LongFraction z) {
        if (z.numer > n || z.denom > n || z.numer >= z.denom) {
            return;
        }

        all.add(x.add(y).add(z));
    }

    public static void main(String[] args) {
        Tester.test(new Task_180());
    }
}
