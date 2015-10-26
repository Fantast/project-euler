package opener15;

import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;
import utils.triples.IntTriple;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//Answer :
public class Task_34_2 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_34_2());
        Logger.close();
    }

    int basic[][] = new int[][]{
            new int[]{2, 3, 4},
            new int[]{2, 4, 4},
            new int[]{2, 4, 5},
            new int[]{6, 6, 6},
            new int[]{2, 6, 10}
    };
    int primes[][] = new int[30][3];
    boolean can[][][] = new boolean[100][100][100];

    Set<IntTriple> olds = new HashSet<>();
    Set<IntTriple> tmp = new HashSet<>();
    Set<IntTriple> news = new HashSet<>();
    Set<IntTriple> news2 = new HashSet<>();
    Set<IntTriple> all = new HashSet<>();

    public void solving() {
//        createPrimes();
        primes = basic;
        for (int[] p : primes) {
            olds.add(new IntTriple(p[0], p[1], p[2]));
            olds.add(new IntTriple(p[0], p[2], p[1]));
            olds.add(new IntTriple(p[1], p[0], p[2]));
            olds.add(new IntTriple(p[1], p[2], p[0]));
            olds.add(new IntTriple(p[2], p[0], p[1]));
            olds.add(new IntTriple(p[2], p[1], p[0]));
        }
        news = new HashSet<>(olds);

        while (news.size() != 0) {
            System.out.println("News: " + news.size() + "; Olds: " + olds.size());
            news2.clear();
            for (IntTriple to : olds) {
                for (IntTriple tn : news) {
                    if (compatible(to, tn)) {
                        putnew(to.a, to.b, to.c + tn.c);
                    }
                }
            }
            olds.addAll(news2);
            tmp = news;
            news = news2;
            news2 = tmp;
        }

        for (IntTriple t : olds) {
            int tt[] = new int[]{t.a, t.b, t.c};
            Arrays.sort(tt);
            all.add(new IntTriple(tt[0], tt[1], tt[2]));
        }

        long res = 0;
        for(IntTriple t : all) {
            res += Long.parseLong("" + t.a + t.b + t.c);
        }
        System.out.println(res);
    }

    private void putnew(int a, int b, int c) {
        if (c >= 100) {
            return;
        }
        putnew2(a, b, c);
        putnew2(a, c, b);
        putnew2(b, a, c);
        putnew2(b, c, a);
        putnew2(c, a, b);
        putnew2(c, b, a);
    }

    private void putnew2(int a, int b, int c) {
        IntTriple newt = new IntTriple(a, b, c);
        if (c < 100 && !olds.contains(newt)) {
            news2.add(newt);
        }
    }

    boolean compatible(IntTriple t1, IntTriple t2) {
        return t1.a == t2.a && t1.b == t2.b && (t1.c + t2.c < 100);
    }

    private void createPrimes() {
        int ind = 0;
        for (int[] b : basic) {
            set(ind++, b[0], b[1], b[2]);
            set(ind++, b[0], b[2], b[1]);
            set(ind++, b[1], b[0], b[2]);
            set(ind++, b[1], b[2], b[0]);
            set(ind++, b[2], b[0], b[1]);
            set(ind++, b[2], b[1], b[0]);
        }
    }

    private void set(int ind, int a, int b, int c) {
        primes[ind][0] = a;
        primes[ind][1] = b;
        primes[ind][2] = c;
    }
}
