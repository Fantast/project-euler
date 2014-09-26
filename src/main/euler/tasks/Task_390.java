package tasks;

import utils.log.Logger;

import static java.lang.Math.sqrt;

//Answer :
public class Task_390 implements ITask {
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

        long N = 100000000;
        long N2 = N*N;

        long res = 0;
        long k2;
        long m2;
        for (long k = 1; k <= N; ++k) {
            k2 = k*k;
            for (long m = 1; m < k; ++m) {
                m2 = m*m;
                long S2 = k2 + m2 + 4*k2 * m2;
                if  (S2 > N2) {
                    break;
                }

                long S = (long) sqrt(S2);
                if (S*S == S2) {
                    res += S;
                }
            }
        }
        System.out.println(res);
    }
}
