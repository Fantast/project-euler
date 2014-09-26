package tasks;

import java.util.ArrayList;
import java.util.TreeSet;

import static utils.MyMath.*;

//Answer :
public class Task_248 implements ITask {

    long ps[];
    private final long PHI = 6227020800L;
    TreeSet<Long> all = new TreeSet<Long>();
    ArrayList<Long> pr = new ArrayList<Long>();

    public void solving() {
        ps = getCachedPrimes();

        for (long div : getDivisors(6227020800L)) {
            if (isPrime(div + 1)) {
                pr.add(div + 1);
            }
        }

        fill(15, 0, PHI, 1);
        System.out.println(all.size());
//        for (long p : all) {
//            System.out.println(p);
//        }

//        int count = 0;
//        for (long n = 6227180920L; count < 150000; ++n) {
//            if (isOk(n)) {
//                ++count;
//                System.out.println(count + ": " + n);
//                System.out.println("     " + MyMath.getPrimeDivisors(n));
//            }
//        }
    }

    boolean isOk(long n) {
        long phi = n;
        for (int i = 0; i < ps.length; ++i) {
            long p = ps[i];

            if (n % p == 0) {
                phi = (phi - phi / p);
                if (phi < PHI) {
                    return false;
                }

                while (n % p == 0) {
                    n /= p;
                }
                if (n == 1) {
                    break;
                }
            }

            if (p * p >= n) {
                if (phi % n == 0 && (phi - phi / n == PHI) && isPrime(n)) {
//                if (isPrime(n)) {
                    phi = (phi - phi / n);
                }
                break;
            }
        }
        return phi == PHI;
    }

    public static void main(String[] args) {
        Tester.test(new Task_248());
    }

    private void fill(int depth, int first, long phi, long n) {
        if (phi == 1) {
            all.add(n);
            return;
        }
        
        if (depth == 0) {
            return;
        }
        
        for (int i = first; i < pr.size(); ++i) {
            long p = pr.get(i);

            if (phi%(p-1) == 0) {
                fill(depth - 1, i+1, phi / (p-1), n*p);
            }
        }
    }
}
