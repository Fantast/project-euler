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
        MyMath.primes(100000);
        long s = 0;
        for (long m = 1; m <= 100; ++m) {
            progress1000(m);
            int mm = check(m);
            if (mm != -1) {
                System.out.print(mm  + " ");
//                if ((m + 4 - mm)%(mm-1) == 0) {
////                    System.out.println(m  + ": " + mm);
//                    s+= m;
//                }
            }
        }
//        System.out.println(s);
    }

    private int check(long m) {
//        System.out.println(m);
        TOP: for (int p = 2; p <= m+4; ++p) {
            for (int a = 1; a <= m; ++a) {
//                if (MyMath.modPow(a, p, m) != a%m) {
                if (MyMath.modPow(a, p, m) != 1) {
                    continue TOP;
                }
            }
//            System.out.println("  " + p);
            return p;
        }
        return -1;
    }
}
