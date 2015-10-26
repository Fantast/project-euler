package opener15;

import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;

//Answer :
public class Task_34 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_34());
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

    public void solving() {
        createPrimes();
        for (int[] p : primes) {
            can[p[0]][p[1]][p[2]] = true;
        }

        long res = 0;
        for (int a = 1; a < 100; ++a) {
            progress(a);
            for (int b = a; b < 100; ++b) {
                for (int c = b; c < 100; ++c) {
                    if (can[a][b][c]) {
                        res += Long.parseLong("" + a + b + c);
//                        System.out.println(a + " " + b + " " + c + ": " + res);

                        for (int[] p : primes) {
                            for (int i = a; i < 100; i += p[0]) {
                                for (int j = a; j < 100; j += p[1]) {
                                    for (int k = a; k < 100; k += p[2]) {
                                        can[i][j][k] = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(res);
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
