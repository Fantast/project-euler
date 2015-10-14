package opener15;

import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;

import static java.math.BigInteger.valueOf;
import static jodd.util.StringUtil.reverse;
import static utils.MyMath.*;
import static utils.OtherUtils.padRight;

//Answer: 20918955604925018282747256506500
public class Task_30 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_30());
        Logger.close();
    }

    String data[] = new String[] {
            "               ",
            " XXX  X  X XXX ",
            "   X X X X X   ",
            "  X  X X X XX  ",
            " X   X X X   X ",
            " XXX  X  X XX  ",
            "               "
    };

//    String data[] = new String[] {
//            "     ",
//            "  XX ",
//            " X X ",
//            "   X ",
//            "     "
//    };

    int w = data.length;
    int h = data[0].length();

    Grid aliveGrid = findCellSolutions(true);
    Grid deadGrid = findCellSolutions(false);
    Grid firstAliveGrid = aliveGrid.filterWithEmptyBorder(true);
    Grid lastAliveGrid = aliveGrid.filterWithEmptyBorder(false);
    Grid firstDeadGrid = deadGrid.filterWithEmptyBorder(true);
    Grid lastDeadGrid = deadGrid.filterWithEmptyBorder(false);

    public void solving() {
        Grid res = findRowSolutions(getRow(0), 0);
        for (int r = 1; r < h; ++r) {
            res = res.addOnBottom(findRowSolutions(getRow(r), r));
        }
        res = res.transpose();
        res.next().print();
        res.solve();
    }

    private Grid findRowSolutions(int row, int ind) {
        Grid rowGrid = isBitSet(row, 0) ? firstAliveGrid : firstDeadGrid;
        for (int c = 1; c < w; ++c) {
            Grid cellGrid = c == w - 1
                    ? isBitSet(row, w-1) ? lastAliveGrid : lastDeadGrid
                    : isBitSet(row, c) ? aliveGrid : deadGrid;

            rowGrid = rowGrid.addOnBottom(cellGrid);
        }
        rowGrid = rowGrid.transpose();

        if (ind == 0) {
            rowGrid = rowGrid.filterWithEmptyBorder(true);
        }
        if (ind == h - 1) {
            rowGrid = rowGrid.filterWithEmptyBorder(false);
        }
        return rowGrid;
    }

    private static final int[] BIT_COUNT = {0, 1, 1, 2, 1, 2, 2, 3};
    private Grid findCellSolutions(boolean needAlive) {
        ArrayList<int[]> variants = new ArrayList<>();
        for (int r1 = 0; r1 < 8; ++r1) {
            int al1 = BIT_COUNT[r1 & 0b111];
            for (int r2 = 0; r2 < 8; ++r2) {
                boolean isAlive = isBitSet(r2, 1);
                int al2 = BIT_COUNT[r2 & 0b111] - (isAlive ? 1 : 0);
                for (int r3 = 0; r3 < 8; ++r3) {
                    int al3 = BIT_COUNT[r3 & 0b111];
                    int aliveCount = al1 + al2 + al3;

                    boolean newAlive = (aliveCount == 3) || (isAlive && aliveCount == 2);
                    if (newAlive == needAlive) {
                        variants.add(new int[]{r1, r2, r3});
                    }
                }
            }
        }
        return new Grid(3, 3, variants);
    }

    private int getRow(int r) {
        int row = 0;
        for (int i = 0; i < w; ++i) {
            if (data[i].charAt(r) != ' ') {
                row = setBit(row, i);
            }
        }
        return row;
    }

    public static class Grid {
        public static final Comparator<int[]> arrayComparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                for (int i = 0; i < o1.length; ++i) {
                    if (o1[i] < o2[i]) {
                        return -1;
                    }
                    if (o1[i] > o2[i]) {
                        return 1;
                    }
                }
                return 0;
            }
        };

        int w;
        int h;

        Collection<int[]> variants;

        public Grid(int w, int h, Collection<int[]> newVariants) {
            this.w = w;
            this.h = h;
            variants = newVariants;
        }

        public Grid addOnBottom(Grid other) {
            if (w != other.w) {
                throw new IllegalArgumentException("Should be of equal widths");
            }

            Collection<int[]> newVariants = new TreeSet<>(arrayComparator);

            for (int[] v1 : variants) {
                int last1 = v1[h - 2];
                int last2 = v1[h - 1];

                for (int[] v2 : other.variants) {
                    if (last1 == v2[0] && last2 == v2[1]) {
                        newVariants.add(merge(v1, v2));
                    }
                }
            }

            return new Grid(w, h + other.h - 2, newVariants);
        }

        public Grid filterWithEmptyBorder(boolean upBorder) {
            Collection<int[]> newVariants = new ArrayList<>();

            for (int[] v : variants) {
                if ((upBorder && v[0] == 0) || (!upBorder && v[h - 1] == 0)) {
                    newVariants.add(v);
                }
            }
            return new Grid(w, h, newVariants);
        }

        public Grid transpose() {
            Collection<int[]> newVariants = new ArrayList<>();
            for (int[] v : variants) {
                int newv[] = new int[w];
                for (int c = 0; c < w; ++c) {
                    int row = 0;
                    for (int r = 0; r < h; ++r) {
                        if (isBitSet(v[r], c)) {
                            row = setBit(row, r);
                        }
                    }
                    newv[c] = row;
                }
                newVariants.add(newv);
            }

            return new Grid(h, w, newVariants);
        }

        public Grid next() {
            Collection<int[]> newVariants = new TreeSet<>(arrayComparator);
            for (int[] v : variants) {
                int newv[] = new int[h];
                for (int r = 1; r < h - 1; ++r) {
                    int row = 0;
                    for (int c = 1; c < w - 1; ++c) {
                        if (willBeAlive(v, r, c)) {
                            row = setBit(row, c);
                        }
                    }
                    newv[r] = row;
                }
                newVariants.add(newv);
            }

            return new Grid(w, h, newVariants);
        }

        private boolean willBeAlive(int[] v, int r, int c) {
            int aliveCount = 0;
            boolean isAlive = isBitSet(v[r], c);
            for (int i = -1; i <= 1; ++i) {
                for (int j = -1; j <= 1; ++j) {
                    if (i == 0 && j == 0) {
                        continue;
                    }
                    if (isBitSet(v[(r + i)], c + j)) {
                        aliveCount++;
                    }
                }
            }

            return aliveCount == 3 || (isAlive && aliveCount == 2);
        }

        private int[] merge(int[] v1, int[] v2) {
            int v[] = new int[v1.length + v2.length - 2];
            System.arraycopy(v1, 0, v, 0, v1.length);
            System.arraycopy(v2, 2, v, v1.length, v2.length - 2);
            return v;
        }

        public void solve() {
            BigInteger b2015 = valueOf(2015);

            int vr[] = null;
            BigInteger vn = null;
            int min = 1000000000;
            for (int v[] : variants) {
                String sn = "";
                int bits = 0;
                for (int i = 1; i < v.length-1; i++) {
                    int a = v[i];
                    bits += bitCount(a);
                    sn += padRight(reverse(Integer.toBinaryString(a >> 1)), 15, '0');
                }
                BigInteger n = new BigInteger(sn, 2);
                if (bits < min && n.mod(b2015).longValueExact() == 1315) {
                    min = bits;
                    vr = v;
                    vn = n;
                }
            }

            print(vr);
            System.out.println(vn);
        }

        public void print() {
            variants.forEach(this::print);
        }

        private void print(int[] v) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            for (int r = 1; r < h-1; ++r) {
                int row = v[r];
                for (int c = 1; c < w-1; ++c) {
                    System.out.print(isBitSet(row, c) ? "*" : "_");
                }
                System.out.println();
            }
        }
    }
}
