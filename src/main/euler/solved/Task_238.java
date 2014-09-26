package solved;

import tasks.ITask;
import tasks.Tester;

import static utils.STLUtils.reverse;

//Answer : 9922545104535661
public class Task_238 implements ITask {
    long LIM = 2*1000000000000000L;

    int S0 = 14025256;
    int n = 20300713;
    int ind[] = new int[n];
    int dCnt = 0;
    int dSum = 0;
    byte d[] = new byte[40000000];
    int ds[] = new int[20000000];

    public void solving() {
        int cnt = 0;
        int s = S0;
        while (true) {
            ind[s] = ++cnt;
            fillDigits(s);

            s = (int) (s * (long) s % n);

            if (ind[s] != 0) {
                break;
            }
        }

        for (int i = 1; i <= dCnt; ++i) {
            ds[i] = ds[i-1] + d[i];
        }
        System.arraycopy(d, 1, d, dCnt+1, dCnt);

        long res = 0;
        for (int i = 1; i <= dSum; ++i) {
            if (i%1000000 == 0) {
                System.out.println("Progress: " + i);
            }
            int b = 1;
            int e = findEnd(i);

            int sm = ds[e];
            TOP:
            while (true) {
                while (sm < i && e < b+dCnt) {
                    sm += (int) d[++e];
                }
                if (sm == i) {
                    break TOP;
                }

                sm -= d[b] + (int) d[e];
                --e;
                ++b;
                if (b > dCnt) {
                    b = 0;
                    break;
                }
            }

            res += b*(1L + (LIM - i) / dSum);
        }

        System.out.println(res);
    }
    private void fillDigits(int n) {
        int oDc = dCnt;
        while (n != 0) {
            byte dig = (byte) (n % 10);
            n /= 10;

            dSum += dig;
            d[++dCnt] = dig;
        }
        reverse(d, oDc + 1, dCnt);
    }

    private int findEnd(int n) {
        int lf = 0;
        int rg = dCnt;
        while (lf != rg - 1) {
            int m = (lf + rg) / 2;
            if (ds[m] >= n) {
                rg = m;
            } else {
                lf = m;
            }
        }
        return lf;
    }

    public static void main(String[] args) {
        Tester.test(new Task_238());
    }
}
