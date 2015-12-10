package tasks;

import utils.MyMath;
import utils.log.Logger;

//Answer :
public class Task_536 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_536());
        Logger.close();
    }

    public void solving() {
        MyMath.primes(100);
        System.out.println(check(21));
        for (long m = 1; m <= 113; ++m) {
            int mm = check(m);
            if (mm != -1) {
                System.out.println(m  + ": " + mm);
//                System.out.print(m  + ",");
            }
//            long phi = MyMath.phi(m);
//            long m3 = m+3;
//            if ((m+3)%phi == 0 && m3/phi > 1) {
//                System.out.println(m);
//            }
//            System.out.println(phi);
        }
    }

    private int check(long m) {
        System.out.println(m);
        TOP: for (int p = 6; p <= m+4; ++p) {
            for (int a = 1; a <= m; ++a) {
//                if (MyMath.modPow(a, p, m) != a%m) {
                if (MyMath.modPow(a, p, m) != 1) {
                    continue TOP;
                }
            }
            System.out.println("  " + p);
//            return p;
        }
        return -1;
    }
}
