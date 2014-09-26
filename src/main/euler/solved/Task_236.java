package solved;

import tasks.ITask;
import tasks.Tester;
import utils.LongFraction;

import java.util.TreeSet;

//Answer : 123/59
//it's still way TOO slow
public class Task_236 implements ITask {
    long A1 = 5248;
    long A2 = 1312;
    long A3 = 2624;
    long A4 = 5760;
    long A5 = 3936;
    long B1 = 640;
    long B2 = 1888;
    long B3 = 3776;
    long B4 = 3776;
    long B5 = 5664;

    long AS = A1 + A2 + A3 + A4 + A5;
    long BS = B1 + B2 + B3 + B4 + B5;

    public void solving() {
        TreeSet<LongFraction> ms = new TreeSet<LongFraction>();
        
        for (long b1 = 1; b1 < B1; ++b1) {
            System.out.println("B1: " + b1);
            HERE:
            for (long a1 = 1; a1 < A1; ++a1) {
                long mn = b1 * A1;
                long md = B1 * a1;
                LongFraction m1 = new LongFraction(mn, md);
                if (m1.compareTo(LongFraction.ONE) < 0) {
                    continue;
                }

                LongFraction p2 = new LongFraction(B2 * mn, A2 * md);
                long pn2 = p2.numer;
                long pd2 = p2.denom;

                for (long b2 = pn2, a2 = pd2; b2 <= B2 && a2 <= A2; b2 += pn2, a2 += pd2) {
                    LongFraction p3 = new LongFraction(B3 * mn, A3 * md);
                    long pn3 = p3.numer;
                    long pd3 = p3.denom;


                    for (long b3 = pn3, a3 = pd3; b3 <= B3 && a3 <= A3; b3 += pn3, a3 += pd3) {
                        LongFraction p4 = new LongFraction(B4 * mn, A4 * md);
                        long pn4 = p4.numer;
                        long pd4 = p4.denom;

                        F4: for (long b4 = pn4, a4 = pd4; b4 <= B4 && a4 <= A4; b4 += pn4, a4 += pd4) {
                            LongFraction p5 = new LongFraction(B5 * mn, A5 * md);
                            long pn5 = p5.numer;
                            long pd5 = p5.denom;

                            long as = a1+a2+a3+a4;
                            long bs = b1+b2+b3+b4;

                            long pkn = mn*AS*bs - md*BS*as;
                            long pkd = md*BS*pd5-mn*AS*pn5;
                            if (pkn % pkd != 0) continue F4;

                            long k = pkn / pkd;
                            if (k < 0) continue;
                            
                            long b5 = k*pn5;
                            long a5 = k*pd5;

                            LongFraction m = new LongFraction((a1 + a2 + a3 + a4 + a5) * (B1 + B2 + B3 + B4 + B5),
                                    (A1 + A2 + A3 + A4 + A5) * (b1 + b2 + b3 + b4 + b5));
                            if (m.equals(m1)) {
                                System.out.println("Foundad M: " + m);
                                ms.add(m);
                                System.out.println("Currently best: " + ms.last());
                                continue HERE;
                            }
                        }
                    }
                }
            }
        }

        System.out.println(ms.first());
    }

    public static void main(String[] args) {
        Tester.test(new Task_236());
    }
}
