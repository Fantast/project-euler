package tasks;

import utils.log.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static utils.MyMath.factor;

//Answer :
public class Task_223 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_223());
        Logger.close();
    }

    public int LIM = 40;
    public int LIMA = LIM / 3;

    public void solving() {
        long res = (LIM - 1) / 2; //a==1, b==c

        long a2, b2;
        for (long a = 2; a <= LIMA; ++a) {
            Map<Long, Integer> factors = factor(a - 1);
            factor(a + 1).forEach((k, v) -> factors.merge(k, v, Integer::sum));

            List<Fact> all = new ArrayList<>();
            factors.forEach((k, v) -> {all.add(new Fact(k, v)); });

            int twoCount = factors.getOrDefault(2, 0);

            res += count(a, twoCount, all, 0, 1, (a-1)*(a+1));
        }
    }

    private long count(long a, int twoCount, List<Fact> f, int ind, int m, long n) {
        if (m >= n) {
            return 0;
        }

        if (ind == twoCount && m%2 != n%2) {
            return 0;
        }

        long curr = f.get(ind);

        count(a, ind + 1, all, m*curr, n/curr);

        return 0;
    }

    public static class Fact implements Comparable<Fact> {
        long n;
        int cnt;

        public Fact(long n, int cnt) {
            this.n = n;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Fact o) {
            return Long.compare(n, o.n);
        }
    }
}
