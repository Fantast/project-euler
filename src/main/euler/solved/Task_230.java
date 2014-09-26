package solved;

import tasks.ITask;
import tasks.Tester;

//Answer : 850481152593119296
public class Task_230 implements ITask {
    String A = "1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679";
    String B = "8214808651328230664709384460955058223172535940812848111745028410270193852110555964462294895493038196";
    long k = 104683731294243150L;

    long LEN = A.length();

    long sev[] = new long[18];
    long ten[] = new long[18];
    long fs[] = new long[75];

    public void solving() {
        sev[0] = 1;
        ten[0] = 1;
        for (int i = 1; i < 18; ++i ) {
            sev[i] = 7*sev[i-1];
            ten[i] = 10*ten[i-1];
        }

        fs[1] = LEN;
        fs[2] = LEN;

        long f1 = LEN;
        long f2 = LEN;
        int cnt = 2;
        while (f2 < k) {
            ++cnt;
            long f3 = f1 + f2;
            f1 = f2;
            f2 = f3;

            fs[cnt] = f3;
        }

        System.out.println(D(35));

        long s = 0;
        for (int i = 0; i <= 17; ++i) {
            s += ten[i] * D( f(i) );
        }

        System.out.println(s);
    }

    long D(long n) {
        int ind = -1;
        for (int i = 1; i < fs.length; ++i) {
            if (fs[i] >= n) {
                ind = i;
                break;
            }
        }

        while (ind > 2) {
            if (n <= fs[ind-2]) {
                ind -= 2;
            } else {
                ind -= 1;
                n -= fs[ind-1];
            }
        }

        int ni = (int) (n - 1);
        return (ind == 1 ? A.charAt(ni) : B.charAt(ni)) - '0';
    }

    long f(int n) {
        return (127L+19L*n)*sev[n];
    }

    public static void main(String[] args) {
        Tester.test(new Task_230());
    }
}
