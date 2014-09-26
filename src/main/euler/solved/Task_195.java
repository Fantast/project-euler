package solved;

import tasks.ITask;
import tasks.Tester;

import static java.lang.Math.sqrt;
import static utils.MyMath.gcd;

//Answer : 75085391
//@see: http://pythag.net/node10.html
//        double p = (a+b+c);
//        double r = sqrt((p-2*a)*(p-2*b)*(p-2*c)/p/4);
//
//        due to http://pythag.net/node10.html
//        (a, b, c) is integer-sides of 60-deg triangle, iff (m > n) && (m-n)%3 != 0
//
//        1)
//          a = k*(2*m*n + m*m);
//          b = k*(2*m*n + n*n);
//          c = k*(m*m + n*n + m*n);
//          p = 5*m*n + 2*m*m + 2*n*n
//          pa = m*n + 2*n*n = n*(m+2n)
//          pb = m*n + 2*m*m = m*(n+2m)
//          pc = 3*m*n
//          pabc = 3*m2*n2*(5*m*n+2*m2+2*n2)
//          pr = sqrt(3)/2*m*n
//
//        or
//        2)
//          a = k*(m*m - n*n);
//          b = k*(2*m*n + m*m);
//          c = k*(m*m + n*n + m*n);
//          p = 3*m*n + 3*m2 = 3*m*(m+n)
//          pa = 3*m*n + m2 + 2*n2 = (m+n)*(m+2n)
//          pb = m2 - m*n = m*(m-n)
//          pc = m*n + m2 - 2*n2 = (m-n)*(m+2n)
//          pabc = m * (m+n) * (m-n)^2 * (m+2n)^2
//          pr = (m-n)*(m+2*n)/(2*sqrt(3))
public class Task_195 implements ITask {
//    private static final long N = 1053779;
    private static final long N = 153779;

    private static final double sq3 = sqrt(3);
    private static final double NM3 = 2*N*sq3;
    private static final double ND3 = 2.0*N/sq3;

    long res = 0;

    public void solving() {
        long res = 0;
        for (long r = 1; r <= NM3; ++r) {
            for (long dmn = 1; dmn * dmn < r; ++dmn) {
                if (r % dmn == 0) {
                    long dmx = r / dmn;

                    if ((dmx - dmn) % 3 == 0) {
                        // m - n = dmn
                        // m + 2*n = dmx
                        long m = 2*dmn + dmx;
                        if (m%3 == 0) {
                            m /= 3;
                            long n = (dmx - dmn) / 3;
                            if ((m - n) % 3 != 0 && gcd(m, n) == 1) {
                                res += NM3/r;
                            }
                        }
                    } else if (r<=ND3 && gcd(dmn, dmx) == 1) {
                        // m = dmx;
                        // n = dmn;
                        res += ND3/r;
                    }
                }
            }
        }

        System.out.println(res);
    }

    public static void main(String[] args) {
        Tester.test(new Task_195());
    }
}
