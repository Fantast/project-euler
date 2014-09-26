package solved;

import tasks.ITask;
import tasks.Tester;
import utils.MyMath;
import utils.log.Logger;

import java.util.Arrays;

//Answer : 8495585919506151122
//see: http://oeis.org/A060839 for some thoughts
public class Task_272 implements ITask {

    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_272());
        Logger.close();
    }

    long LIM = 100000000000L;

    long[] needprimes;
    int np;
    int op;

    long fr[] = new long[210000];
    long fr9[] = new long[210000];

    public void solving() {
        long hiprime = LIM / (9 * 7 * 13 * 19);
        needprimes = MyMath.getPrimesBetween(0, hiprime);

        Arrays.fill(fr, -1);

        fr[0] = fr[1] = 1;
        fr9[0] = fr9[1] = 1;
        for (int i = 2; i < fr.length; ++i) {
            boolean fit = true;
            for (long p : MyMath.getPrimeDivisors(i)) {
                if (p%3 == 1) {
                    fit = false;
                    break;
                }
            }
            fr[i] = fr[i-1] + (fit && i%9!=0 ? i : 0);
            fr9[i] = fr9[i-1] + (fit ? i : 0);
        }

        np = 0;
        op = 0;
        for (long p : needprimes) {
            if (p%3 == 1) {
                needprimes[np++] = p;
            }
        }

        long res = 0;
        for (int i = 0; i < needprimes.length; ++i) {
            System.out.println(i);
            res += find(i, 0, needprimes[i]);
        }
        System.out.println(res);
    }

    private long find(int ind, int cnt, long n) {
        long p = needprimes[ind];
        long res = 0;
        for (long nn = n; nn <= LIM; nn*=p) {
            if (cnt == 4) {
                res += nn * fr[((int) (LIM / nn))];
            } else {
                if (cnt == 3 && nn*9 <= LIM) {
                    res += nn * 9 * fr9[((int) (LIM / nn / 9))];
                }
                for (int nind = ind+1; np-nind+cnt+1 >= 4 && nn*needprimes[nind] <= LIM; ++nind) {
                    res += find(nind, cnt + 1, nn*needprimes[nind]);
                }
            }
        }

        return res;
    }
}
