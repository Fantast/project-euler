package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//Answer : 2252639041804718029
public class Task_297 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_297());
        Logger.close();
    }

    final long MAX = 100000000000000000L - 1;
    
    long f[] = new long[100];
    int cnt = 0;
    public void solving() {
        f[cnt++] = 1;
        f[cnt++] = 2;
        long f1 = 1;
        long f2 = 2;
        while (f2 <= MAX) {
            f[cnt] = f1 + f2;
            f1 = f2;
            f2 = f[cnt++];
        }
        --cnt;

        System.out.println(count(MAX));
    }

    Map<Long, Long> cache = new HashMap<Long, Long>();
    private long count(long n) {
        if (n == 1) return 1;

        Long res = cache.get(n);
        if (res == null) {
            long fn = closestFib(n);

            if (fn == n) {
                res = 1L + count(n-1);
            } else {
                res = n - fn + count(n - fn) + count(fn);
            }
            cache.put(n, res);
        }
        return res;
    }

    private long closestFib(long n) {
        int i = Arrays.binarySearch(f, 0, cnt, n);
        if (i >= 0) return f[i];
        return f[-i - 2];
    }
}
