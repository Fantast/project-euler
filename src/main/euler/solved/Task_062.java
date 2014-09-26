package solved;

import tasks.ITask;

import java.util.Arrays;
import java.util.HashMap;

import static utils.STLUtils.reverse;

//Answer : 127035954683
public class Task_062 implements ITask {
    HashMap<Long, Integer> cnts = new HashMap<Long, Integer>();

    public void solving() {
//        for (long i = 345; ; ++i ) {
        for (long i = 9000; ; --i ) {
            long state = getState( i*i*i );

            Integer cnt = cnts.get( state );
            if (cnt == null) cnt = 0;
            ++cnt;

            if (cnt == 5) {
                System.out.println(i*i*i);
                break;
            }
            cnts.put(state, cnt);
        }
    }

    long d[] = new long[20];
    private long getState(long n) {
        int k = digits(n, d);
        Arrays.sort( d, 0, k);
        reverse(d, 0, k-1);
        return getNumb(d, k);
    }

    private long getNumb(long[] d, int n) {
        long tenp = 1;
        long res = 0;
        for (int i = n-1; i >= 0; --i) {
            res += d[i] * tenp;
            tenp *= 10;
        }

        return res;
    }

    private int digits(long n, long d[]) {
        int len = 0;
        while (n != 0) {
            d[len++] = n%10;
            n /= 10;
        }
        return len;
    }
}
