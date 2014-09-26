package solved;

import tasks.ITask;
import tasks.Tester;

import java.util.Arrays;

import static utils.MyMath.isPrime;

//Answer : 612407567715
public class Task_111 implements ITask {

    long pow10[] = {
        1, 10, 100, 1000, 10000, 100000, 1000000, 10000000,
        100000000, 1000000000, 10000000000L, 100000000000L
    };
    private int dCnt = 10;
    int nd[] = new int[dCnt];
    long sum = 0;

    public void solving() {
        long res = 0;
        for (int d = 0; d < 10; ++d) {
            System.out.println("Processing digit: " + d);
            Arrays.fill(nd, d);
            res += find(d);
        }
        System.out.println(res);
    }

    private long find(int d) {
        sum = 0;
        for (int c = dCnt - 1; c > 0; --c) {
            fillEmpty(d, 0, dCnt - c);
            if (sum != 0) {
                break;
            }
        }

        return sum;
    }

    private void fillEmpty(int digit, int beg, int emCnt) {
        if (emCnt == 0) {
            checkPrime();
            return;
        }

        int end = dCnt - emCnt;
        if (digit == 0 && beg == 0) {
            end = 0;
        }
        for (int i = beg; i <= end; ++i) {
            for (int d = i == 0 ? 1 : 0; d < 10; ++d) {
                if (d == digit) {
                    continue;
                }
                nd[i] = d;
                fillEmpty(digit, i + 1, emCnt - 1);
                nd[i] = digit;
            }
        }
    }

    public static void main(String[] args) {
        Tester.test(new Task_111());
    }

    private void checkPrime() {
        long n = 0;
        for (int i = 0; i < dCnt; ++i) {
            n += nd[i] * pow10[dCnt - i - 1];
        }
        if (isPrime(n)) {
            sum += n;
        }
    }
}
