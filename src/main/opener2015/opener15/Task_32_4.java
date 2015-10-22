package opener15;

import tasks.AbstractTask;
import tasks.Tester;
import utils.OtherUtils;
import utils.log.Logger;

import java.util.*;

import static utils.MyMath.isBitSet;
import static utils.MyMath.setBit;

//Answer :
@SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
public class Task_32_4 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_32_4());
        Logger.close();
    }

    int cells = 2*3*3*5;
    int max[] = new int[] {2, 3, 3, 5};
    int deltas[] = new int[] {45, 15, 5, 1};
    public int[] toCoords(int ind) {
        return new int[] {
                ind / 45,
                (ind % 45) / 15,
                (ind % 15) / 5,
                ind % 5
        };
    }

//    int cells = 2*2*2*5; // 31488
//    int max[] = new int[] {2, 2, 2, 5};
//    int deltas[] = new int[] {20, 10, 5, 1};
//    public int[] toCoords(int ind) {
//        return new int[] {
//                ind / 20,
//                (ind % 20) / 10,
//                (ind % 10) / 5,
//                ind % 5
//        };
//    }

    int pc;
    int pieces[][][];

    Node pieceTree;

    public void solving() {
        genereatePieces();
        System.out.println(pc);

        System.out.println(find(new BitSet(cells)));
    }

    private void genereatePieces() {
        Set<Piece> all = new TreeSet<>();
        findPiece(all, new HashSet<>(), new Cube(), new Cube(), 0);

        Set<Cube> cubes = new TreeSet<>();
        pieces = new int[all.size()][4][4];
        pc = 0;
        for (Piece p : all) {
            int[][] piece = pieces[pc++];

            int j = 0;
            for (Cube c : p.cubes) {
                cubes.add(c);
                piece[j++] = c.coords;
            }
//            System.out.println(Arrays.deepToString(piece));
        }
//        for (Cube c : cubes) {
//            System.out.println(Arrays.toString(c.coords));
//        }

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

    Map<BitSet, Long> ff = new HashMap<>();

    int count = 0;
    long find(BitSet state) {
        ++count;
        if (count < 9) {
            System.out.println("Current: " + count);
        }

        if (count > 0) {
            Long fill = fill(state);
            --count;
            return fill;
        }

        Long res = ff.get(state);
        if (res == null) {
            BitSet newState = (BitSet) state.clone();
            res = fill(state);
            ff.put(newState, res);
        }
        --count;
        return res;
    }

    private Long fill(BitSet state) {
        int ind = state.nextClearBit(0);
        if (ind >= cells) {
//            System.out.println("Got some...");
            return 1L;
        }

        state.set(ind);

        int coords[] = toCoords(ind);

        long res = 0;

        for (Node n1 : pieceTree.children) {
            int ind1 = newInd(ind, coords, n1.cube);
            if (ind1 == -1 || state.get(ind1)) {
                continue;
            }
            state.set(ind1);
            for (Node n2 : n1.children) {
                int ind2 = newInd(ind, coords, n2.cube);
                if (ind2 == -1 || state.get(ind2)) {
                    continue;
                }
                state.set(ind2);
                for (Node n3 : n2.children) {
                    int ind3 = newInd(ind, coords, n3.cube);
                    if (ind3 == -1 || state.get(ind3)) {
                        continue;
                    }
                    state.set(ind3);
                    for (Node n4 : n3.children) {
                        int ind4 = newInd(ind, coords, n4.cube);
                        if (ind4 == -1 || state.get(ind4)) {
                            continue;
                        }
                        state.set(ind4);
                        res += find(state);
                        state.clear(ind4);
                    }
                    state.clear(ind3);
                }
                state.clear(ind2);
            }
            state.clear(ind1);
        }

        state.clear(ind);

        return res;
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
