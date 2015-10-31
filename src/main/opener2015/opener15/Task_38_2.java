package opener15;

import tasks.AbstractTask;
import tasks.Tester;
import utils.FileUtils;
import utils.log.Logger;

import java.io.IOException;

//Answer :
public class Task_38_2 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_38_2());
        Logger.close();
    }

    int steps = 2 * 128 * 128 * 128 + 128 + 5;

    int DELTA = 60;
    int MAX = DELTA * 2;

    Cell affected[] = new Cell[MAX * MAX * MAX * MAX];
    int isAffected[][][][] = new int[MAX][MAX][MAX][MAX];
    int affectedCnt = 0;

    Cell changed[] = new Cell[MAX * MAX * MAX * MAX];
    int changedCnt = 0;
    int current = 0;

    int nbs[][][][] = new int[MAX][MAX][MAX][MAX];
    boolean field[][][][] = new boolean[MAX][MAX][MAX][MAX];

    public void solving() throws IOException {
        for (String cell : FileUtils.readLines("/downloads/cells.txt")) {
            cell = cell.replaceAll(" +", "");
            String[] sp = cell.substring(1, cell.length() - 1).split(",");

            int na = pi(sp[0]) + DELTA;
            int nb = pi(sp[1]) + DELTA;
            int nc = pi(sp[2]) + DELTA;
            int nd = pi(sp[3]) + DELTA;

            if (!field[na][nb][nc][nd]) {
                field[na][nb][nc][nd] = true;
                Cell cc = new Cell(na, nb, nc, nd);
                changed[changedCnt++] = cc;
                affected[affectedCnt++] = cc;
                isAffected[na][nb][nc][nd] = 1;
            }
        }

        for (int step = 1; step <= 20; ++step) {
            for (int i = 0; i < changedCnt; ++i) {
                Cell cell = changed[i];
                boolean state = field[cell.a][cell.b][cell.c][cell.d];
                int dnb = state ? 1 : -1;
                for (int a = -1; a < 2; ++a) {
                    for (int b = -1; b < 2; ++b) {
                        for (int c = -1; c < 2; ++c) {
                            for (int d = -1; d < 2; ++d) {
                                if (a == 0 && b == 0 && c == 0 && d == 0) {
                                    continue;
                                }

                                int na = cell.a + a;
                                int nb = cell.b + b;
                                int nc = cell.c + c;
                                int nd = cell.d + d;
                                nbs[na][nb][nc][nd] += dnb;

                                if (isAffected[na][nb][nc][nd] != step) {
                                    isAffected[na][nb][nc][nd] = step;
                                    affected[affectedCnt++] = new Cell(na, nb, nc, nd);
                                }
                            }
                        }
                    }
                }
                current += dnb;
            }

            System.out.println();
            System.out.println(step + " changed   : " + changedCnt);
            System.out.println(step + " affected  : " + affectedCnt);
            System.out.println(step + " current   : " + current);

            changedCnt = 0;
            for (int i = 0; i < affectedCnt; ++i) {
                Cell cell = affected[i];
                int na = cell.a;
                int nb = cell.b;
                int nc = cell.c;
                int nd = cell.d;
                int nbc = nbs[na][nb][nc][nd];
                boolean state = field[na][nb][nc][nd];

                if (state) {
                    if (nbc < 9 || nbc > 11) {
                        field[na][nb][nc][nd] = false;
                        changed[changedCnt++] = cell;
                    }
                } else {
                    // empty cell
                    if (nbc == 10) {
                        field[na][nb][nc][nd] = true;
                        changed[changedCnt++] = cell;
                    }
                }
            }
            affectedCnt = 0;
        }
    }

    public class Cell implements Comparable<Cell> {
        public final int a;
        public final int b;
        public final int c;
        public final int d;
        private final int hashCode;

        public Cell(int a, int b, int c, int d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
            hashCode = new Integer(a + b + c + d).hashCode();
        }

        public int hashCode() {
            return hashCode;
        }

        public boolean equals(Object obj) {
            Cell p = (Cell) obj;
            return a == p.a && b == p.b && c == p.c && d == p.d;
        }

        public int compareTo(Cell q) {
            int ret = (a < q.a) ? -1 : (a == q.a ? 0 : 1);
            if (ret != 0) return ret;

            ret = (b < q.b) ? -1 : (b == q.b ? 0 : 1);
            if (ret != 0) return ret;

            ret = (c < q.c) ? -1 : (c == q.c ? 0 : 1);
            if (ret != 0) return ret;

            return (d < q.d) ? -1 : (d == q.d ? 0 : 1);
        }

        @Override
        public String toString() {
            return "{" + a + "," + b + "," + c + "," + d + '}';
        }
    }
}
