package opener15;

import tasks.AbstractTask;
import tasks.Tester;
import utils.OtherUtils;
import utils.log.Logger;

import java.util.*;

import static utils.MyMath.isBitSet;
import static utils.MyMath.setBit;

//Answer: 2032707264
@SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
public class Task_32 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_32());
        Logger.close();
    }

    int cells = 5*2*3*3;
    int max[] = new int[] {999, 2, 3, 3};
    int deltas[] = new int[] {18, 9, 3, 1};
    public int[] toCoords(int ind) {
        return new int[] {
                ind / 18,
                (ind % 18) / 9,
                (ind % 9) / 3,
                ind % 3
        };
    }

//    int cells = 5*2*2*2; // 31488
//    int max[] = new int[] {999, 2, 2, 2};
//    int deltas[] = new int[] {8, 4, 2, 1};
//    public int[] toCoords(int ind) {
//        return new int[] {
//                ind / 8,
//                (ind % 8) / 4,
//                (ind % 4) / 2,
//                ind % 2
//        };
//    }

    int layerVolume = cells/5;
    int FULL_LAYER = (1 << layerVolume) - 1;

    int pc;
    int pieces[][][];

    Node pieceTree;

    long currentIncrement;
    Map<Long, Long> tmp;
    Map<Long, Long> counts = new HashMap<>();
    Map<Long, Long> counts2 = new HashMap<>();

    public void solving() {
        genereatePieces();

        counts.put(0L, 1L);

        for (int i = 0; i < 5; ++i) {
            System.out.println(i + ": " + counts.size());
            counts2.clear();
            int processed = 0;
            for (Map.Entry<Long, Long> e : counts.entrySet()) {
                progress1000(processed++);
                currentIncrement = e.getValue();
                fill(e.getKey());
            }
            tmp = counts;
            counts = counts2;
            counts2 = tmp;
        }

        System.out.println(counts.get(0L));
    }

    private void genereatePieces() {
        Set<Piece> all = new TreeSet<>();
        findPiece(all, new HashSet<>(), new Cube(), new Cube(), 0);

        pieces = new int[all.size()][4][4];
        pc = 0;
        for (Piece p : all) {
            int[][] piece = pieces[pc++];

            int j = 0;
            for (Cube c : p.cubes) {
                piece[j++] = c.coords;
            }
        }
        pieceTree = buildNode(null, Arrays.asList(pieces), 0);
    }

    private void findPiece(Set<Piece> all, Set<Cube> used, Cube start, Cube end, int isBent) {
        if (isBent == 0b1111) {
            all.add(new Piece(used));
            return;
        }

        for (int d = 0; d < 4; ++d) {
            if (!isBitSet(isBent, d)) {
                int newBent = setBit(isBent, d);
                for (int dir = -1; dir <= 1; dir += 2) {
                    Cube newCube = new Cube(start, d, dir);
                    if (!used.contains(newCube)) {
                        used.add(newCube);
                        findPiece(all, used, newCube, end, newBent);
                        used.remove(newCube);
                    }

                    newCube = new Cube(end, d, dir);
                    if (!used.contains(newCube)) {
                        used.add(newCube);
                        findPiece(all, used, start, newCube, newBent);
                        used.remove(newCube);
                    }
                }
            }
        }
    }

    private Node buildNode(int[] rootCube, List<int[][]> children, int cubeInd) {
        TreeMap<int[], List<int[][]>> grouped = new TreeMap<>(OtherUtils.arrayComparator);
        for (int[][] piece : children) {
            int[] cube1 = piece[cubeInd];

            List<int[][]> groupList = grouped.get(cube1);
            if (groupList == null) {
                groupList = new ArrayList<>();
                grouped.put(cube1, groupList);
            }

            groupList.add(piece);
        }

        ArrayList<Node> nodeChildren = new ArrayList<>();

        for (Map.Entry<int[], List<int[][]>> e : grouped.entrySet()) {
            int[] childCube = e.getKey();
            List<int[][]> groupedChildren = e.getValue();

            Node child;
            if (cubeInd == 3) {
                child = new Node(childCube, null);
            } else {
                child = buildNode(childCube, groupedChildren, cubeInd + 1);
            }

            nodeChildren.add(child);
        }

        return new Node(rootCube, nodeChildren.toArray(new Node[nodeChildren.size()]));
    }

    public void fill(long state) {
        if ((state & FULL_LAYER) == FULL_LAYER) {
            addCount2(state >> layerVolume, currentIncrement);
            return;
        }

        int ind = Long.numberOfTrailingZeros(~state);

        state = setBit(state, ind);

        int coords[] = toCoords(ind);

        for (Node n1 : pieceTree.children) {
            int ind1 = newInd(ind, coords, n1.cube);
            if (ind1 == -1 || isBitSet(state, ind1)) {
                continue;
            }
            long state1 = setBit(state, ind1);
            for (Node n2 : n1.children) {
                int ind2 = newInd(ind, coords, n2.cube);
                if (ind2 == -1 || isBitSet(state1, ind2)) {
                    continue;
                }
                long state2 = setBit(state1, ind2);
                for (Node n3 : n2.children) {
                    int ind3 = newInd(ind, coords, n3.cube);
                    if (ind3 == -1 || isBitSet(state2, ind3)) {
                        continue;
                    }
                    long state3 = setBit(state2, ind3);
                    for (Node n4 : n3.children) {
                        int ind4 = newInd(ind, coords, n4.cube);
                        if (ind4 == -1 || isBitSet(state3, ind4)) {
                            continue;
                        }
                        long state4 = setBit(state3, ind4);
                        fill(state4);
                    }
                }
            }
        }
    }

    public int newInd(int ind, int coords[], int[] cube) {
        int delta = 0;
        for (int d = 0; d < 4; ++d) {
            int thisMax = max[d];
            int thisD = coords[d];
            int cubeD = cube[d];
            int newD = thisD + cubeD;
            if (newD < 0 || newD >= thisMax) {
                return -1;
            }
            delta += cubeD * deltas[d];
        }

        return ind + delta;
    }

    public void addCount2(long state, long count) {
        Long cc = counts2.get(state);
        if (cc == null) {
            cc = count;
        } else {
            cc += count;
        }
        counts2.put(state, cc);
    }

    static class Cube implements Comparable<Cube> {
        final int coords[];

        public Cube() {
            this.coords = new int[4];
        }

        public Cube(Cube prev, int d, int dir) {
            coords = prev.coords.clone();
            coords[d] += dir;
        }

        @Override
        public boolean equals(Object o) {
            return this == o || Arrays.equals(coords, ((Cube) o).coords);

        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(coords);
        }

        int zeroes(int[] a) {
            int z = 0;
            for (int e : a) {
                if (e == 0) {
                    z++;
                }
            }
            return z;
        }

        @Override
        public int compareTo(Cube o) {
            if (zeroes(coords) > zeroes(o.coords)) {
                return -1;
            }
            if (zeroes(coords) < zeroes(o.coords)) {
                return 1;
            }
            return OtherUtils.arrayComparator.compare(coords, o.coords);
        }
    }

    static class Piece implements Comparable<Piece> {
        final TreeSet<Cube> cubes;

        public Piece(Set<Cube> cubes) {
            this.cubes = new TreeSet<>(cubes);
        }

        @Override
        public boolean equals(Object o) {
            return this == o || cubes.equals(((Piece) o).cubes);
        }

        @Override
        public int hashCode() {
            return cubes.hashCode();
        }

        @Override
        public int compareTo(Piece o) {
            Iterator<Cube> it = o.cubes.iterator();
            for (Cube cube1 : cubes) {
                Cube cube2 = it.next();
                int cmp = cube1.compareTo(cube2);
                if (cmp != 0) {
                    return cmp;
                }
            }
            return 0;
        }
    }

    static class Node {
        int[] cube;
        Node children[];

        public Node(int[] cube, Node[] children) {
            this.cube = cube;
            this.children = children;
        }
    }
}
