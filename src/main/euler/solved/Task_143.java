package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import java.util.Map;
import java.util.TreeMap;

//Answer : 30758397 in ~3 min.
public class Task_143 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_143());
        Logger.close();
    }

    long LIM = 120000;
    public void solving() {
        System.out.println("Searching pairs....");
        for (long p = 1; p < LIM/2; ++p) {
            System.out.println("P: " + p);
            for (long q = p; p+q < LIM; ++q) {
                long sq = p*p + q*q + p*q;
                long a = (long) Math.sqrt(sq);
                if (a*a == sq) {
                    putFound(p, q, a);
                }
            }
        }

        long res = 0;
        System.out.println("Searching triples....");
        boolean f[] = new boolean[(int) (LIM+1)];
        for (Map.Entry<Long, TreeMap<Long, Long>> e : found.entrySet()) {
            Long p = e.getKey();
            System.out.println("P: " + p);

            for (Map.Entry<Long, Long> e2 : e.getValue().entrySet()) {
                Long q = e2.getKey();

                Long a = e2.getValue();

                TreeMap<Long, Long> rs = found.get(q);
                if (rs != null) {
                    for (Map.Entry<Long, Long> e3 : rs.entrySet()) {
                        Long r = e3.getKey();

                        Long b = e3.getValue();

                        Long c = e.getValue().get(r);
                        if (c != null) {
                            int s = (int) (p+q+r);
                            if (s <= 120000 && !f[s]) {
                                f[s] = true;
                                res += (long)s;
                            }
                        }
                    }
                }
            }
        }

        System.out.println(res);
    }

    TreeMap<Long, TreeMap<Long, Long>> found = new TreeMap<Long, TreeMap<Long, Long>>();
    private void putFound(long p, long q, long a) {
        TreeMap<Long, Long> f = found.get(p);
        if (f == null) {
            f = new TreeMap<Long, Long>();
            found.put(p, f);
        }
        f.put(q, a);
    }
}
