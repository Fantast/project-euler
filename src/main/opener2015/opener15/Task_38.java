package opener15;

import tasks.AbstractTask;
import tasks.Tester;
import utils.FileUtils;
import utils.log.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//Answer :
public class Task_38 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_38());
        Logger.close();
    }

    int steps = 2 * 128 * 128 * 128 + 128 + 5;

    Map<Cell, Cell> all = new HashMap<>();
    Set<Cell> changed = new HashSet<>();
    Set<Cell> affected = new HashSet<>();
    int current = 0;

    public void solving() {
        for (String cell : FileUtils.readLines("/downloads/cells.txt")) {
            cell = cell.replaceAll(" +", "");
            String[] sp = cell.substring(1, cell.length() - 1).split(",");
            Cell c = new Cell(pi(sp[0]), pi(sp[1]), pi(sp[2]), pi(sp[3]));
            changed.add(c);
            all.put(c, c);
        }

        for (int step = 0; step < steps; ++step) {
            affected.clear();
            for (Cell cell : changed) {
                boolean state = cell.state;
                for (int a = -1; a < 2; ++a) {
                    for (int b = -1; b < 2; ++b) {
                        for (int c = -1; c < 2; ++c) {
                            for (int d = -1; d < 2; ++d) {
                                if (a == 0 && b == 0 && c == 0 && d == 0) {
                                    continue;
                                }

                                Cell key = new Cell(cell.a + a, cell.b + b, cell.c + c, cell.d + d);
                                Cell nc = all.get(key);
                                if (nc == null) {
                                    nc = key;
                                    all.put(key, nc);
                                }

                                nc.nbCount += state ? -1 : 1;

                                affected.add(nc);
                            }
                        }
                    }
                }
                current += state ? -1 : 1;
                cell.state = !state;
            }

            System.out.println(step + " changed   : " + changed.size());
            System.out.println(step + " affected  : " + affected.size());
            System.out.println(step + " all.size(): " + all.size());
            System.out.println(step + " current   : " + current);

            changed.clear();
            for (Cell cell : affected) {
                if (cell.state) {
                    if (cell.nbCount < 9 || cell.nbCount > 11) {
                        changed.add(cell);
                        if (cell.nbCount == 0) {
                            //become empty with no neighbours
                            all.remove(cell);
                        }
                    }
                } else {
                    // empty cell
                    if (cell.nbCount == 10) {
                        changed.add(cell);
                    } else {
                        if (cell.nbCount == 0) {
                            //empty with no neighbours
                            all.remove(cell);
                        }
                    }
                }
            }
        }
    }

    public class Cell implements Comparable<Cell> {
        public final int a;
        public final int b;
        public final int c;
        public final int d;
        private final int hashCode;

        boolean state;
        int nbCount;

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
            return a == p.a && b == p.b && c == p.c;
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
    }
}
