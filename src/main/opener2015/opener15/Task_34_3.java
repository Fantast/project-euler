package opener15;

import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;
import utils.triples.IntTriple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Answer :
public class Task_34_3 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_34_3());
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

    List<IntTriple> olds = new ArrayList<>();
    List<IntTriple> tmp = new ArrayList<>();
    List<IntTriple> news = new ArrayList<>();
    List<IntTriple> news2 = new ArrayList<>();

    public void solving() {
//        createPrimes();
        primes = basic;
        for (int[] p : primes) {
            olds.add(new IntTriple(p[0], p[1], p[2]));
        }
        news = new ArrayList<>(olds);

        while (news.size() != 0) {
            System.out.println("News: " + news.size() + "; Olds: " + olds.size());
            news2.clear();
            for (IntTriple to : olds) {
                for (IntTriple tn : news) {
                    combine(to, tn);
                }
            }
            olds.addAll(news2);
            tmp = news;
            news = news2;
            news2 = tmp;
        }

        long res = 0;
        for(IntTriple t : olds) {
            res += Long.parseLong("" + t.a + t.b + t.c);
        }
        System.out.println(res);
    }

    void combine(IntTriple t1, IntTriple t2) {
        putnew(t1.a, t1.b, t1.c, t2.a, t2.b, t2.c);
        putnew(t1.a, t1.c, t1.b, t2.a, t2.c, t2.b);
        putnew(t1.b, t1.a, t1.c, t2.b, t2.a, t2.c);
        putnew(t1.b, t1.c, t1.a, t2.b, t2.c, t2.a);
        putnew(t1.c, t1.a, t1.b, t2.c, t2.a, t2.b);
        putnew(t1.c, t1.b, t1.a, t2.c, t2.b, t2.a);
    }

    private void putnew(int a1, int b1, int c1, int a2, int b2, int c2) {
        if (a1 == a2 && b1 == b2) {
            putnew(a1, b1, c1, c2);
        }
    }

    int sr[] = new int[3];
    private void putnew(int a, int b, int c1, int c2) {
        for (int c = c1; c < 100; c += c2) {
            sr[0] = a;
            sr[1] = b;
            sr[2] = c;
            Arrays.sort(sr);

            if (sr[2] < 100 && !can[sr[0]][sr[1]][sr[2]]) {
                can[sr[0]][sr[1]][sr[2]] = true;
                news2.add(new IntTriple(sr[0], sr[1], sr[2]));
            }
        }
        for (int c = c2; c < 100; c += c1) {
            sr[0] = a;
            sr[1] = b;
            sr[2] = c;
            Arrays.sort(sr);

            if (sr[2] < 100 && !can[sr[0]][sr[1]][sr[2]]) {
                can[sr[0]][sr[1]][sr[2]] = true;
                news2.add(new IntTriple(sr[0], sr[1], sr[2]));
            }
        }
    }
}
