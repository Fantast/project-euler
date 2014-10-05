package solved;

import tasks.AbstractTask;
import tasks.Tester;

import java.util.*;

import static utils.MyMath.gcd;

//Answer :
// based on http://oeis.org/A002487
public class Task_175 extends AbstractTask {
//        public int a = 17;
//        public int b = 13;
    public int a = 987654321;
    public int b = 123456789;

    public void solving() {
        int gcd = gcd(a, b);

        a /= gcd;
        b /= gcd;

        System.out.println(gcd);
        System.out.println(a + " " + b);

        System.out.println("Backtracking...");

        long ops[] = new long[14000000];
        int opcount = 0;
        while (!(a == 1 && b == 1)) {
//            System.out.println(a + " " + b);
            if (a > b) {
                // a == A(2*n + 1) = A(n) + A(n+1)
                // b == A(2*(n+1)) = A(n+1)
                // new_n = (n - 1) / 2
                a = a - b;
                ops[opcount++] = 1;
            } else {
                // a == A(2*n) = A(n)
                // b == A(2*n + 1) = A(n) + A(n + 1)
                // new_n = n/2
                b = b - a;
                ops[opcount++] = 2;
            }
        }

        System.out.println("Computing...");

        List<Integer> res = new ArrayList<>();
        int ones = 1;
        int zeros = 0;
        for (int i = opcount - 1; i >= 0; --i) {
            if (ops[i] == 1) {
                //old_n = n * 2 + 1
                ones++;
                if (zeros != 0) {
                    res.add(zeros);
                    zeros = 0;
                }
            } else {
                //old_n = n * 2
                zeros++;
                if (ones != 0) {
                    res.add(ones);
                    ones = 0;
                }
            }
        }
        for (int a : res) {
            System.out.print(a + ", ");
        }
        System.out.println(ones != 0 ? ones : zeros);
    }

    private void test(int n) {
        System.out.println(n + ": brute: " + bruteCount(n));
    }

    private long bruteCount(int n) {
        Set<List<Integer>> all = new HashSet<>();
        for (int a = 0; a < n; ++a) {
            int b = n - a;

            List<Integer> ads = new ArrayList<>();
            for (int j = 0; j < 2; ++j) {
                int p2 = 1;
                while (b != 0) {
                    if ((b & 1) != 0) {
                        ads.add(p2);
                    }
                    p2 <<= 1;
                    b >>= 1;
                }
                b = a;
            }
            Collections.sort(ads, Comparator.<Integer>reverseOrder());
            all.add(ads);
        }
//        for (List<Integer> e : all) {
//            System.out.println(e);
//        }
        return all.size();
    }

    public static void main(String[] args) {
        Tester.test(new Task_175());
    }
}
