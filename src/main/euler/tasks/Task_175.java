package tasks;

import java.util.Arrays;

import static utils.MyMath.gcd;

//Answer :
public class Task_175 implements ITask {
    long BEG = 13717420;
    public void solving() {
        System.out.println(gcd(123456789,987654321));
        System.out.println(123456789/9 + " " + 987654321/9);

        Arrays.fill(d, -1);
        long fn1 = count(Long.toBinaryString(BEG - 1), 0);
        for (long n = BEG; ;++n) {
            Arrays.fill(d, -1);
            long fn = count(Long.toBinaryString(n), 0);

            long g = gcd(fn, fn1);
            if (fn/g == 13717421 && fn1/g==109739369) {
                System.out.println(n);
                break;
            }
//            System.out.println(fn + "/" + fn1);

            fn1 = fn;
        }
    }

    long d[] = new long[200];
    private long count(String s, int i) {
        if (i == s.length()) return 1;
        if (d[i] != -1) return d[i];

        int j, k;
        long r = 0;

        j = nextInd(s, i+1, '1');
        if (j == s.length()) return d[i] = j - i;

        int c = j - i;

        k = nextInd(s, j+1, '1');
        if (k != s.length()) {
            r += c * count(s, k);
        } else {
            r = c;
        }

        k = nextInd(s, j+1, '0');
        if (k != s.length()) {
            r += (c+1)*count(s, k);
        }

        return d[i] = r;
    }

    private int nextInd(String s, int beg, char ch) {
        for (int k = beg; k < s.length(); ++k) {
            if (s.charAt(k) == ch) {
                return k;
            }
        }
        return s.length();
    }

    public static void main(String[] args) {
        Tester.test(new Task_175());
    }
}
