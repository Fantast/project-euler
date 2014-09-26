package solved;

import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;

import java.math.BigInteger;

import static java.math.BigInteger.valueOf;
import static utils.MyMath.gcd;

//Answer : 17971254122360635
public class Task_153 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_153());
        Logger.close();
    }


    int LIM = 100000000;
//    int LIM = 100000;
//    int LIM = 1000000;

    int sqrtLim = (int) (Math.sqrt(LIM));

    long divsums[] = new long[LIM + 1];
    long complexdivsums[] = new long[LIM + 1];

    public void solving() {
        for (int a = 1; a <= sqrtLim; ++a) {
            progress100(a);
            int a2 = a*a;
            int limb = (int) Math.sqrt(LIM - a2);
            for (int b = a; b <= limb; ++b) {
                int g = gcd(a, b);
                int ag = a/g;
                int bg = b/g;
                int ab2 = (ag*a + bg*b);
                for (int k = g, n = ab2*k; n <= LIM; k++, n += ab2) {
                    complexdivsums[n] += 2L*a;
                    if (a != b) {
                        complexdivsums[n] += 2L*b;
                    }

                    if (k != g) {
                        complexdivsums[n] += 2L*ag*k;
                        if (a != b) {
                            complexdivsums[n] += 2L*bg*k;
                        }
                    }
                }
            }
        }

        System.out.println(complexdivsums[LIM]);

        for (int a = 1; a <= sqrtLim; ++a) {
            progress100(a);
            divsums[a*a] += a;
            for (int b = a+1, n = a*(a+1); n <= LIM; b++, n += a) {
                divsums[n] += a;
                divsums[n] += b;
            }
        }
        System.out.println(divsums[LIM]);

        BigInteger sum = BigInteger.ZERO;
        for (int n = 1; n <= LIM; ++n) {
            sum = sum.add(valueOf(divsums[n])).add(valueOf(complexdivsums[n]));
        }
        System.out.println();
        System.out.println(sum);
    }
}
