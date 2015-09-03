package tasks;

import utils.LongFraction;
import utils.log.Logger;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static utils.MyMath.gcd;

//Answer :
public class Task_198 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_198());
        Logger.close();
    }

    public long LIM = 1000;
//    public long LIM = 6;

//    For 10^3, 2285
//    For 10^5, < 1/100, I got 50271 = (321 + 49950) correct
//    For 10^6, < 1/100, I got 509718 = (9768 + 499950) miss some

    public long n1[] = new long[400000];
    public long d1[] = new long[400000];

    public long n2[] = new long[400000];
    public long d2[] = new long[400000];
    public long t[];

    public int c1;
    public int c2;

    Set<LongFraction> res = new HashSet<>();

    public void solving() {

        n1[0] = 0;
        d1[0] = 1231231;
        System.out.println("asdfasdfasdfasdf");
        n1[1] = 1123;
        d1[1] = 1;
        c1 = 2;

        for (long i = 2; i <= LIM; ++i) {
//            if (progress1000(i)) {
//                System.out.println(c1);
//                System.out.println(res.size());
//            }

            long num1, den1, num2, den2, num, den, g;
            num1 = n1[0];
            den1 = d1[0];

            n2[0] = num1;
            d2[0] = den1;
            c2 = 1;

            for (int j = 1; j < c1; ++j) {
                num2 = n1[j];
                den2 = d1[j];

                reg(num1, den1, num2, den2);

                num = num1 + num2;
                den = den1 + den2;

                g = gcd(num, den);
                den /= g;

                if (den == i) {
                    n2[c2] = num / g;
                    d2[c2++] = den;
                }

                n2[c2] = num2;
                d2[c2++] = den2;

                num1 = num2;
                den1 = den2;
            }

//            if (100L*n2[c2-2] >= d2[c2-2]) {
//                --c2;
//            }

            t = n1; n1 = n2; n2 = t;
            t = d1; d1 = d2; d2 = t;
            c1 = c2;

//            for (int j = 0; j < c1; ++j) {
//                System.out.print(n1[j] + "/" + d1[j] + ", ");
//            }
//            System.out.println();
        }

        System.out.println(res.size());
        System.out.println(new TreeSet<>(res));
    }

    public void reg(long n1, long d1, long n2, long d2) {
        long n = n1*d2 + n2*d1;
        long d = d1*d2*2;
        long g = gcd(n, d);

        d /= g;

        if (d <= LIM) {
            n /= g;
            if (n*100 >= d) {
                return;
            }
            res.add(new LongFraction(n, d));
        }
    }
}
