package tasks;

import utils.log.Logger;

import static java.lang.Math.sqrt;

//Answer :
public class Task_390 extends AbstractTask{
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_390());
        Logger.close();
    }

    public void solving() {
//        0.5*sq( b2 + c2 + b2*c2 ) = S <= n
//        b2 + c2 + b2*c2 = 4*S2 <= 4*n2
//        b = 2k;  c = 2m;
//
//        k^2 + m^2 + 4*k^2*m^2 = S^2 <= n^2
//
//        (m - k)^2 + 2*m*k*(2*m*k + 1) = S^2;
//
//        a^2 + b*(b+1) = S^2
//        a^2 + b^2 + b = S^2
//
//        a + b + 4*a*b = S^2

        long N = 10000;
        long N2 = N*N;
        System.out.println(N);
        System.out.println(N2);

        long res = 0;
        long k2;
        long m2;
        for (long k = 1; k <= N; ++k) {
            progress10000000(k);
            k2 = k*k;
            for (long m = k; m <= N; ++m) {
                m2 = m*m;
                long S2 = k2 + m2 + 4*k2 * m2;
//                if  (S2 > N2) {
//                    break;
//                }

                long S = (long) sqrt(S2);
                if (S*S == S2) {
                    res += S;
                    long b = 2*k;
                    long c = 2*m;
                    System.out.println(b + " " + c + ": " + (b*b*c*c + b*b + c*c));
                }
            }
        }
        System.out.println(res);
    }
}
