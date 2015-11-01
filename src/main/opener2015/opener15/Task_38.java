package opener15;

import tasks.AbstractTask;
import tasks.Tester;
import utils.FileUtils;
import utils.log.Logger;

import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

import static utils.OtherUtils.padLeft;

//Answer : -136501880806045292
public class Task_38 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_38());
        Logger.close();
    }

    int steps = 2 * 128 * 128 * 128 + 128 + 5 - 1;
    int baseStep = steps - (steps - 400) / 12 * 12;

    int st12 = (steps - baseStep) / 12;

    Map<Cell, Cell> all = new HashMap<>();
    Set<Cell> changed = new HashSet<>();
    Set<Cell> affected = new HashSet<>();
    Map<CellGroup, CellGroup> states = new HashMap<>();
    int current = 0;

    List<CellGroup> groups = new ArrayList<>();

    public void solving() throws IOException {
        for (String cell : FileUtils.readLines("/downloads/task38/" + padLeft("" + baseStep, 4, '0') + ".points")) {
            if (cell.isEmpty()) {
                if (all.size() == 0) {
                    System.out.println("Shouldn't happen");
                }
                groups.add(runGroup());
                progress(groups.size());
                continue;
            }
            cell = cell.replaceAll(" +", "");

            assert !cell.isEmpty();

            String[] sp = cell.substring(1, cell.length() - 1).split(",");

            Cell c = new Cell(pi(sp[0]), pi(sp[1]), pi(sp[2]), pi(sp[3]));
            c.state = true;

            if (!changed.contains(c)) {
                changed.add(c);
                affected.add(c);
                all.put(c, c);
            }
        }

        assert all.size() == 0;

        BigInteger res = BigInteger.ZERO;
        for (CellGroup group : groups) {
            for (Cell c : group.translate((12 / group.cycle) * st12)) {
                res = res.add(c.value());
            }
        }
        System.out.println(res);
    }

    private CellGroup runGroup() throws IOException {
//        System.out.print(all.size() + ": ");

        int steps = -1;
        CellGroup res;
        while (true) {
            ++steps;
            List<Cell> stateCells = new ArrayList<>();
            for (Cell c : all.keySet()) {
                if (c.state) {
                    stateCells.add(c);
                }
            }

            CellGroup cg = CellGroup.normalize(stateCells);
            CellGroup ex = states.get(cg);
            if (ex != null) {
                ex.setd(cg.ma - ex.ma, cg.mb - ex.mb, cg.mc - ex.mc, cg.md - ex.md);
                ex.cycle = steps;

//                System.out.println(steps + " steps");
//                System.out.println(new Cell(ex.ma, ex.mb, ex.mc, ex.md));
//                System.out.println(new Cell(cg.ma, cg.mb, cg.mc, cg.md));
//                System.out.println(new Cell(ex.da, ex.db, ex.dc, ex.dd));
//                System.out.println();

                res = ex;
                break;
            }
            states.put(cg, cg);

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

            changed.clear();
            for (Cell cell : affected) {
                if (cell.state) {
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
            affected.clear();
        }

        current = 0;
        all.clear();
        states.clear();
        changed.clear();
        affected.clear();

        return res;
    }

    public static class CellGroup {
        final Set<Cell> cells;
        final int hashCode;
        final int m[] = new int[4];
        final int d[] = new int[4];
        final int ma;
        final int mb;
        final int mc;
        final int md;

        int da;
        int db;
        int dc;
        int dd;
        int cycle;

        public CellGroup(Set<Cell> newCells, int ma, int mb, int mc, int md) {
            cells = newCells;
            m[0] = ma;
            m[1] = mb;
            m[2] = mc;
            m[3] = md;
            this.ma = ma;
            this.mb = mb;
            this.mc = mc;
            this.md = md;
            hashCode = cells.hashCode();
        }

        public void setd(int da, int db, int dc, int dd) {
            d[0] = da;
            d[1] = db;
            d[2] = dc;
            d[3] = dd;
            this.da = da;
            this.db = db;
            this.dc = dc;
            this.dd = dd;
        }

        public int size() {
            return cells.size();
        }

        public Set<Cell> translate(int cycles) {
            Set<Cell> newCells = new HashSet<>();
            for (Cell c : cells) {
                newCells.add(
                        c.translate(ma + da * cycles, mb + db * cycles, mc + dc * cycles, md + dd * cycles)
                );
            }
            return newCells;
        }

        public static CellGroup normalize(List<Cell> cells) {
            int mina, minb, minc, mind;
            mina = minb = minc = mind = 100000;

            Set<Cell> newCells = new HashSet<>();

            for (Cell c : cells) {
                mina = min(mina, c.a);
                minb = min(minb, c.b);
                minc = min(minc, c.c);
                mind = min(mind, c.d);
            }

            for (Cell c : cells) {
                newCells.add(new Cell(c.a - mina, c.b - minb, c.c - minc, c.d - mind));
            }
            return new CellGroup(newCells, mina, minb, minc, mind);
        }

        @Override
        public int hashCode() {
            return hashCode;
        }

        @Override
        public boolean equals(Object o) {
            CellGroup cellGroup = (CellGroup) o;
            return cells.equals(cellGroup.cells);
        }
    }

    public static class Cell implements Comparable<Cell> {
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

        public BigInteger value() {
            return bi(a).multiply(bi(b)).multiply(bi(c)).multiply(bi(d));
        }

        public Cell translate(int da, int db, int dc, int dd) {
            return new Cell(a + da, b + db, c + dc, d + dd);
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
