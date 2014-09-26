package solved;

import tasks.ITask;
import tasks.Tester;

import java.util.Map;
import java.util.TreeMap;

import static utils.MyMath.getCachedPrimes;

//Answer : 18946051
public class Task_251 implements ITask {

    private long LIM = 110000000;
    long prs[];
    TreeMap<Long, Integer> all = new TreeMap<Long, Integer>();
    int factCnt;
    long factors[] = new long[200];
    int factPow[] = new int[200];
    int res = 0;

    public void solving() {
        prs = getCachedPrimes();

        for (long a = 1; a < LIM; ++a) {
            if (a % 1000000 == 0) {
                System.out.println("Progress: " + a);
            }
            all.clear();
            fill(a + 1, 2);
            fill(8 * a - 1, 1);

            Integer n3 = all.get(3L);
            if (n3 == null || n3 < 3) {
                continue;
            }
            all.put(3L, n3 - 3);

            convertToArr();

            findBC(0, a, 1, 1);
        }
        System.out.println(res);
    }

    private void findBC(int ind, long a, long b, long c) {
        if (a + b + c > LIM) {
            return;
        }

        if (ind == factCnt) {
            ++res;
            return;
        }
        for (int cnt = 0; cnt <= factPow[ind]; cnt += 2) {
            long b2 = safePow(b, factors[ind], cnt / 2);
            long c2 = safePow(c, factors[ind], factPow[ind] - cnt);
            if (b2 == ERROR || c2 == ERROR) {
                continue;
            }
            findBC(ind + 1, a, b2, c2);
        }
    }

    final long ERROR = LIM+2;
    public long safePow(long n, long f, int p) {
        long r = n;
        for (int i = 0; i < p; ++i) {
            r *= f;
            if (r > LIM || r < 0) {
                return ERROR;
            }
        }
        return r;
    }

    private void fill(long n, int dc) {
        for (long p : prs) {
            if (p * p > n) {
                break;
            }

            int cnt = 0;
            while (n % p == 0) {
                cnt += dc;
                n /= p;
            }

            if (cnt != 0) {
                addToMap(p, cnt);
            }
        }

        if (n != 1) {
            addToMap(n, dc);
        }
    }

    private void addToMap(long p, int cnt) {
        Integer c = all.get(p);
        if (c == null) {
            c = cnt;
        } else {
            c = c + cnt;
        }

        all.put(p, c);
    }

    private void convertToArr() {
        factCnt = 0;
        for (Map.Entry<Long, Integer> entry : all.entrySet()) {
            factors[factCnt] = entry.getKey();
            factPow[factCnt++] = entry.getValue();
        }
    }

    public static void main(String[] args) {
        Tester.test(new Task_251());
    }
}
