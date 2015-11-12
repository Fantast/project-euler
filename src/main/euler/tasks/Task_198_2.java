package tasks;

import utils.log.Logger;

import static utils.MyMath.gcd;

//Answer :
// see: https://www.wikiwand.com/en/Stern%E2%80%93Brocot_tree
public class Task_198_2 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_198_2());
        Logger.close();
    }

    public long n = 1000;
    public long n2 = n*2;

//    For 10^3, 2285
//    For 10^5, < 1/100, I got 50271 = (321 + 49950) correct
//    For 10^6, < 1/100, I got 509718 = (9768 + 499950) miss some

    long res = 0;

    public void solving() {
        for (int d2 = 1; d2 <= n2; d2+=2) {
            for (int a = 1; a <= n; ++a) {
                for (int b = 1; b <= n; ++b) {
                }
            }
        }
    }

    public void reg(long a1, long b1, long a2, long b2) {
        long a = a1*b2 + a2*b1;
        long b = b1*b2*2;

        long g = gcd(a, b);
        b /= g;

        if (b <= n) {
            ++res;
        }
    }
}
