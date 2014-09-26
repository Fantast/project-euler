package solved;

import tasks.ITask;

import java.math.BigInteger;

//Answer : 55374
//Link   : http://home.att.net/~numericana/answer/numbers.htm#partitions
public class Task_078 implements ITask {
    int MAX = 3000;
    BigInteger d[][] = new BigInteger[MAX][MAX];
    BigInteger LIM = new BigInteger("1000000");
    public void solving() {
        int n = 5;
        for (n = 3; ; ++n) {
            BigInteger r = get(1, n);
            if (r.mod(LIM).longValue() == 0) {
                System.out.println(n);
                return;
            }
        }
    }

    private BigInteger get(int beg, int need) {
        if (need == 0) return BigInteger.ONE;
        if (d[beg][need] != null) return d[beg][need];

        BigInteger res = BigInteger.ZERO;
        for (int i = beg; i <= need; ++i) {
            res = res.add( get(i, need-i) );
        }

        return d[beg][need] = res;
    }
}
