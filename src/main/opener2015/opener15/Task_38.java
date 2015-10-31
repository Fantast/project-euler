package opener15;

import tasks.AbstractTask;
import tasks.Tester;
import utils.FileUtils;
import utils.log.Logger;

import java.io.*;
import java.util.*;

import static utils.OtherUtils.padLeft;

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

    int step;
    PrintWriter out;

    public void solving() throws IOException {
//        for (String cell : FileUtils.readLines("/downloads/cells.txt")) {
        for (String cell : FileUtils.readLines("/downloads/task3800/1000.points")) {
            cell = cell.replaceAll(" +", "");
            if (cell.isEmpty()) {
                continue;
            }
            String[] sp = cell.substring(1, cell.length() - 1).split(",");

            Cell c = new Cell(pi(sp[0]), pi(sp[1]), pi(sp[2]), pi(sp[3]));
            c.invert();

            if (!changed.contains(c)) {
                changed.add(c);
                all.put(c, c);
            }
        }

        for (step = 0; step < steps; ++step) {
//            out = new PrintWriter(
//                    new BufferedWriter(
//                            new FileWriter("/downloads/task38/" + padLeft("" + step, 2, '0') + ".out")));
            PrintStream out = System.out;

            affected.clear();
            for (Cell cell : changed) {
                boolean state = cell.state;
                int dnb = state ? 1 : -1;
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
                                    if (state) {
                                        nc = key;
                                        all.put(nc, nc);
                                        nc.nbCount = 1;
                                        affected.add(nc);
                                    }
                                } else {
                                    nc.nbCount += dnb;
                                    affected.add(nc);
                                }
                            }
                        }
                    }
                }
                current += dnb;
            }

            out.println();
            out.println(step + " changed   : " + changed.size());
            out.println(step + " affected  : " + affected.size());
            out.println(step + " all.size(): " + all.size());
            out.println(step + " current   : " + current);

            changed.clear();
            int affectedFilled = 0;
            for (Cell cell : affected) {
                if (cell.state) {
                    affectedFilled++;
                    if (cell.nbCount < 9 || cell.nbCount > 11) {
                        cell.invert();
                        changed.add(cell);
                        if (cell.nbCount == 0) {
                            //became empty with no neighbours
                            all.remove(cell);
                        }
                    }
                } else {
                    // empty cell
                    if (cell.nbCount == 10) {
                        cell.invert();
                        changed.add(cell);
                    } else {
                        if (cell.nbCount == 0) {
                            //empty with no neighbours
                            all.remove(cell);
                        }
                    }
                }
            }
            out.println(step + " aff.filled : " + affectedFilled);
            if (step > 3) {
                findAreas();
            }
//            out.close();
        }
    }

    HashMap<Cell, Cell> all2 = new HashMap<>();
    Queue<Cell> q = new LinkedList<>();

    private void findAreas() throws IOException {
        PrintWriter cellsOut = new PrintWriter(
                new BufferedWriter(
                        new FileWriter("/downloads/task38/" + padLeft("" + step, 2, '0') + ".points")));

        out.println("Finding areas: " + all.size());

        all2.putAll(all);
        int areaCount = 0;
        while (!all2.isEmpty()) {
            Cell rootCell = null;
            Set<Cell> entries = all2.keySet();
            for (Iterator<Cell> iterator = entries.iterator(); iterator.hasNext(); ) {
                Cell c = iterator.next();
                iterator.remove();
                if (c.state) {
                    rootCell = c;
                    break;
                }
            }

            if (rootCell != null) {
                ++areaCount;
                q.add(rootCell);

                int cellCount = 0;
                while (!q.isEmpty()) {
                    Cell cell = q.poll();
                    cellCount++;
                    cellsOut.println(cell);

                    for (int a = -2; a < 3; ++a) {
                        for (int b = -2; b < 3; ++b) {
                            for (int c = -2; c < 3; ++c) {
                                for (int d = -2; d < 3; ++d) {
                                    Cell nbCell = new Cell(cell.a + a, cell.b + b, cell.c + c, cell.d + d);
                                    nbCell = all2.remove(nbCell);
                                    if (nbCell != null && nbCell.state) {
                                        q.add(nbCell);
                                    }
                                }
                            }
                        }
                    }
                }
                out.println("   new area around cell: " + rootCell + ":" + rootCell.nbCount + " : " + cellCount);
                cellsOut.println();
            }
        }

        out.printf(" total areas: " + areaCount);
        cellsOut.close();
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
            this.state = false;
            hashCode = new Integer(a + b + c + d).hashCode();
        }

        public void invert() {
            state = !state;
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
            return "(" + a + "," + b + "," + c + "," + d + ')';
        }
    }
}
