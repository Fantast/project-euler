package solved;

import tasks.AbstractTask;
import tasks.Tester;
import utils.MyMath;
import utils.log.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static utils.MyMath.factor;

//Answer : 61614848
public class Task_223 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_223());
        Logger.close();
    }

    public int LIM = 25000000;
    public int LIMA = LIM / 3;

    public void solving() {
        MyMath.setMaxPrimesToCache(LIM);

        long res = 0;
        for (long a = LIMA; a > 1; --a) {
            progress10000(a);

            Map<Long, Integer> factors = factor(a - 1);
            factor(a + 1).forEach((k, v) -> factors.merge(k, v, Integer::sum));

            List<Fact> all = new ArrayList<>();
            factors.forEach((k, v) -> {all.add(new Fact(k, v)); });

            res += count(a, all, 0, 1, (a-1)*(a+1));
        }
        System.out.println(res);
        res += (LIM - 1) / 2; //a==1, b==c
        System.out.println(res);
    }

    private long count(long a, List<Fact> f, int ind, long m, long n) {
        if (m >= n) {
            return 0;
        }

        //b >= a  ==>
        if ((n-m)/2 < a) {
            return 0;
        }

        if (ind == f.size()) {
            long b = (n-m)/2;
            long c = (n+m)/2;
            if (a+b+c <= LIM) {
//            System.out.println("-----------------");
//            System.out.println("m: " + m + ", n: " + n);
//            System.out.println("a: " + a + ", b: " + b + ", c: " + c);
                return 1;
            }

            return 0;
        }

        Fact curr = f.get(ind);
        long pm = 1;
        long res = 0;
        for (int i = 0; i <= curr.cnt; ++i) {
            if (!(curr.p == 2 && (i == 0 || i == curr.cnt))) {
                res += count(a, f, ind + 1, m * pm, n / pm);
            }

            pm *= curr.p;
        }

        return res;
    }

    public static class Fact implements Comparable<Fact> {
        long p;
        int cnt;

        public Fact(long p, int cnt) {
            this.p = p;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Fact o) {
            return Long.compare(p, o.p);
        }

        @Override
        public String toString() {
            return "{" + p + ":" + cnt + "}";
        }
    }
}
