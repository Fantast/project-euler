package opener15;

import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;
import utils.pairs.IntPair;
import utils.triples.IntTriple;

import java.util.*;

//Answer :
public class Task_34_3 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_34_3());
        Logger.close();
    }

    int primes[][] = new int[][]{
            new int[]{2, 3, 4},
            new int[]{2, 4, 4},
            new int[]{2, 4, 5},
            new int[]{6, 6, 6},
            new int[]{2, 6, 10}
    };
    boolean can[][][] = new boolean[100][100][100];

    List<IntTriple> tmp = new ArrayList<>();
    List<IntTriple> news = new ArrayList<>();
    List<IntTriple> news2 = new ArrayList<>();

    int tmpindex[][][] = new int[100][100][100];
    int index1[][][] = new int[100][100][100];
    int index2[][][] = new int[100][100][100];

    int index_n1[][] = new int[100][100];
    int index_n2[][] = new int[100][100];

    public void solving() {
        for (int[] p : primes) {
            put(p[0], p[1], p[2]);
        }

        while (news.size() != 0) {
            System.out.println("News: " + news.size());
            news2.clear();

            for (IntTriple tn : news) {
//                multiply(tn);
                combine(tn);
            }
        }

        long res = 0;
        for (int a = 1; a < 100; a++) {
            for (int b = a; b < 100; b++) {
                for (int c = b; c < 100; c++) {
                    if (can[a][b][c]) {
                        res += Long.parseLong("" + a + b + c);
                    }
                }
            }
        }
        System.out.println(res);
    }

    private void multiply(IntTriple tn) {
        for (int a = tn.a; a < 100; a += tn.a) {
            for (int b = closest(a, tn.b); b < 100; b += tn.b) {
                for (int c = closest(b, tn.c); c < 100; c += tn.c) {
                    if (!can[a][b][c]) {
                        put(a,b,c);
                    }
                }
            }
        }
    }

    private void put(int a, int b, int c) {
        indexx(a, b, c);
        indexx(a, c, b);
        indexx(b, a, c);
        indexx(b, c, a);
        indexx(c, a, b);
        indexx(c, b, a);
        news2.add(sortedTriple(a, b, c));
    }

    private void indexx(int a, int b, int c) {
        can[a][b][c] = true;
        index2[a][b][index_n2[a][b]++] = c;
    }

    private int closest(int a, int b) {
        if (a%b == 0) {
            return a;
        }
        return (a/b+1)*b;
    }

    void combine(IntTriple t) {
        putnew(t.a, t.b, t.c, index1.get(MnMxPair(t.a, t.b)));
        putnew(t.a, t.c, t.b, index1.get(MnMxPair(t.a, t.c)));
        putnew(t.b, t.c, t.a, index1.get(MnMxPair(t.b, t.c)));
    }

    void putnew(int a, int b, int c1, List<Integer> cc) {
        int indexLen = index_n1[a][b];
        for (int i = 0; i < indexLen; ++i) {
            int c2 = index1[a][b][i];

            for (int c = c1; c < 100; c += c2) {
                if (!can[a][b][c]) {
                    put(a, b, c);
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

    int st[] = new int[3];
    IntTriple sortedTriple(int a, int b, int c) {
        st[0] = a;
        st[1] = b;
        st[2] = c;
        Arrays.sort(st);
        return new IntTriple(st[0], st[1], st[2]);
    }

    static IntPair MnMxPair(int a, int b) {
        return new IntPair(Math.min(a, b), Math.max(a, b));
    }
}
