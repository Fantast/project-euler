package solved;

import org.apfloat.Apint;
import org.apfloat.ApintMath;
import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import static java.lang.Math.sqrt;
import static utils.MyMath.isExactSquare;

//Answer : 1598174770174689458
public class Task_210 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_210());
        Logger.close();
    }

    long N = 1000000000L;
    public void solving() {
        long n4 = N/4;
        long n8 = N/8;

        long inCircle = 0;
        long r2 = n4*n4/2;
        long r = (long) sqrt(r2);
        for (long i = 1; i <= r; ++i) {
            long sm = r2 - i * i;
            long s = (long) Math.sqrt(sm);

            //correct double precision error :(
            if (s*s > sm) {
                s = ApintMath.sqrt(new Apint(sm))[0].longValue();
            }

            inCircle += s;
        }
        inCircle = 1 + 4*r + 4*inCircle;

        long x1 = n8 - r;
        long x2 = n8 + r;

        int onCircle = 0;
        for (long x = x1; x <= x2; ++x) {
            if (isExactSquare(r2 - (x - n8) * (x - n8))) {
                onCircle += 2;
            }
        }
        System.out.println(sqpts(N, N) - sqpts(N, n4) - 3*n4 + inCircle - onCircle - n4 + 1);
    }

    private long sqpts(long a, long b) {
        return (a+1)*(b+1) + a*b;
    }
}
