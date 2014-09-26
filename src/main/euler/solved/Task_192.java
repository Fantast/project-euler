package solved;

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;
import tasks.ITask;
import tasks.Tester;

import java.math.BigDecimal;
import java.math.BigInteger;

import static java.math.BigInteger.*;
import static utils.MyMath.gcd;
import static utils.MyMath.isExactSquare;


//Answer : 57060635927998347
public class Task_192 implements ITask {

    private static final long LIM = 100000;
    private static final long MAX = 1000000000000L;
    private static final BigInteger BMAX = valueOf(MAX);
    private BigInteger den;
    private BigInteger num;
    private BigDecimal val;
    private static final int PRECISION = 100;

    public void solving() {
        BigInteger res = ZERO;
        for (long n = 2; n <= LIM; ++n) {
            if (n%10000==0) {
                System.out.println("Progress: " + n);
            }
            if (!isExactSquare(n)) {
                doFraction(n);
                res = res.add(den);
            }
        }
        System.out.println(res);
    }

    private void doFraction(long n) {
        String vs = ApfloatMath.sqrt(new Apfloat(n, PRECISION)).toString(true);
        BigDecimal v = new BigDecimal(vs);

        BigDecimal r0 = v;
        BigInteger a0 = r0.toBigInteger();

        BigInteger p0 = ONE;
        BigInteger q0 = ZERO;
        BigInteger p1 = a0;
        BigInteger q1 = ONE;

        BigInteger p2 = ZERO;
        BigInteger q2 = ONE;

        while (true) {
            BigDecimal r1 = BigDecimal.ONE.divide(r0.subtract(new BigDecimal(a0)), PRECISION, BigDecimal.ROUND_HALF_UP);
            BigInteger a1 = r1.toBigInteger();
            p2 = a1.multiply(p1).add(p0);
            q2 = a1.multiply(q1).add(q0);

            BigInteger g = gcd(p2, q2);
            p2 = p2.divide(g);
            q2 = q2.divide(g);

            if (q2.compareTo(BMAX) > 0) {
                num = p1;
                den = q1;
                val = val(p1, q1, v);
                BigInteger p = p0;
                BigInteger q = q0;
                for (; q.compareTo(BMAX) <= 0 && p.compareTo(p2) <= 0; p = p.add(p1), q = q.add(q1)) {
                    if (better(p, q, v)) {
                        num = p;
                        den = q;
                    }
                }

                return;
            }
            p0 = p1;
            p1 = p2;
            q0 = q1;
            q1 = q2;
            a0 = a1;
            r0 = r1;
        }
    }

    private boolean better(BigInteger n1, BigInteger d1, BigDecimal v) {
        BigDecimal nv = val(n1, d1, v);
        if (nv.compareTo(val) < 0) {
            val = nv;
            return true;
        }
        return false;
    }

    private BigDecimal val(BigInteger n, BigInteger d, BigDecimal v) {
        return new BigDecimal(n).divide(new BigDecimal(d), PRECISION, BigDecimal.ROUND_HALF_UP).subtract(v).abs();
    }

    public static void main(String[] args) {
        Tester.test(new Task_192());
    }
}
