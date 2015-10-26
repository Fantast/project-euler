package opener15;

import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;
import utils.triples.IntTriple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Answer : 17186264134
public class Task_34 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_34());
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

    int index1[][][] = new int[100][100][100];
    int index2[][][] = new int[100][100][100];

    int index_n1[][] = new int[100][100];
    int index_n2[][] = new int[100][100];

    public void solving() {
        for (int[] p : primes) {
            put(p[0], p[1], p[2]);
        }
        swap();

        while (news.size() != 0) {
            System.out.println("News: " + news.size());
            news2.clear();

            for (IntTriple tn : news) {
                combine(tn);
            }
            swap();
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

    private void swap() {
        tmp = news;
        news = news2;
        news2 = tmp;

        for (int a = 1; a < 100; ++a) {
            for (int b = 1; b < 100; ++b) {
                int cnt = index_n2[a][b];
                for (int ind = 0; ind < cnt; ++ind) {
                    index1[a][b][index_n1[a][b]++] = index2[a][b][ind];
                }
                index_n2[a][b] = 0;
            }
        }
    }

    private void multiply(IntTriple tn) {
        for (int a = tn.a; a < 100; a += tn.a) {
            for (int b = closest(a, tn.b); b < 100; b += tn.b) {
                for (int c = closest(b, tn.c); c < 100; c += tn.c) {
                    if (!can[a][b][c]) {
                        put(a, b, c);
                    }
                }
            }
        }
    }

    void combine(IntTriple t) {
        putnew(t.a, t.b, t.c);
        putnew(t.a, t.c, t.b);
        putnew(t.b, t.c, t.a);
    }

    void putnew(int a, int b, int c1) {
        int indexLen = index_n1[a][b];
        for (int i = 0; i < indexLen; ++i) {
            int c2 = index1[a][b][i];
            int c = c1 + c2;
            if (c < 100 && !can[a][b][c]) {
                put(a, b, c);
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
        if (!can[a][b][c]) {
            can[a][b][c] = true;
            index2[a][b][index_n2[a][b]++] = c;
        }
    }

    private int closest(int a, int b) {
        if (a % b == 0) {
            return a;
        }
        return (a / b + 1) * b;
    }

    int st[] = new int[3];

    private IntTriple sortedTriple(int a, int b, int c) {
        st[0] = a;
        st[1] = b;
        st[2] = c;
        Arrays.sort(st);
        return new IntTriple(st[0], st[1], st[2]);
    }
}
