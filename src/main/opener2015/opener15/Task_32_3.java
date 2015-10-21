package opener15;

import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

import static utils.MyMath.isBitSet;
import static utils.MyMath.setBit;

//Answer :
public class Task_32_3 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_32_3());
        Logger.close();
    }

    int cells = 2*3*3*5;
//    int cells = 2*2*2*5; // 31488

    public void solving() {
        System.out.println(find(new BitSet(cells)));
    }

    Map<BitSet, Long> ff = new HashMap<>();

    long find(BitSet state) {
        ++count;
        if (count < 8) {
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

    int count = 0;

    private Long fill(BitSet state) {
        int ind = state.nextClearBit(0);
        if (ind >= cells) {
//            System.out.println("Got some...");
            return 1L;
        }

        state.set(ind);

        long res = goStart(state, -1, ind, ind, 0, true);

        state.clear(ind);

        return res;
    }

    private Long goStart(BitSet state, int startInd, int endInd, int currInd, int isBent, boolean justStarted) {
        if (isBent == 0b1111) {
            return find(state);
        }

        long res = 0;
        if (justStarted) {
            for (int d = 0; d < 4; ++d) {
                if (!isBitSet(isBent, d)) {
                    int newBent = setBit(isBent, d);
                    for (int dir = -1; dir <= 1; dir += 2) {
                        int newInd = move(currInd, d, dir);
                        if (newInd != -1 && !state.get(newInd)) {
                            state.set(newInd);
                            res += goStart(state, newInd, endInd, newInd, newBent, false);
                            state.clear(newInd);
                        }
                    }
                }
            }
        } else {
            res += goEnd(state, startInd, endInd, isBent, true);
            for (int d = 0; d < 4; ++d) {
                if (!isBitSet(isBent, d)) {
                    int newBent = setBit(isBent, d);
                    for (int dir = -1; dir <= 1; dir += 2) {
                        int newInd = move(currInd, d, dir);
                        if (newInd != -1 && !state.get(newInd)) {
                            state.set(newInd);
                            res += goStart(state, startInd, endInd, newInd, newBent, false);
                            state.clear(newInd);
                        }
                    }
                }
            }
        }

        return res;
    }

    private Long goEnd(BitSet state, int startInd, int currInd, int isBent, boolean justStarted) {
        if (isBent == 0b1111) {
            return find(state);
        }

        long res = 0;
        if (justStarted) {
            for (int d = 0; d < 4; ++d) {
                if (!isBitSet(isBent, d)) {
                    int newBent = setBit(isBent, d);
                    for (int dir = -1; dir <= 1; dir += 2) {
                        int newInd = move(currInd, d, dir);
                        if (newInd != -1 && newInd > startInd && !state.get(newInd)) {
                            state.set(newInd);
                            res += goEnd(state, startInd, newInd, newBent, false);
                            state.clear(newInd);
                        }
                    }
                }
            }
        } else {
            for (int d = 0; d < 4; ++d) {
                if (!isBitSet(isBent, d)) {
                    int newBent = setBit(isBent, d);
                    for (int dir = -1; dir <= 1; dir += 2) {
                        int newInd = move(currInd, d, dir);
                        if (newInd != -1 && !state.get(newInd)) {
                            state.set(newInd);
                            res += goEnd(state, startInd, newInd, newBent, false);
                            state.clear(newInd);
                        }
                    }
                }
            }
        }

        return res;
    }

    private int move(int ind, int d, int dir) {
//        2×3×3×5

//        int d0 = ind / 45;
//        ind %= 45;
//
//        int d1 = ind / 15;
//        ind %= 15;
//
//        int d2 = ind / 5;
//        int d3 = ind % 5;

        int max;
        int newd;
        int delta;
        switch (d) {
            case 0:
                max = 2;
                delta = 45;
                newd = (ind / 45) + dir;
                break;
            case 1:
                max = 3;
                delta = 15;
                newd = (ind % 45) / 15 + dir;
                break;
            case 2:
                max = 3;
                delta = 5;
                newd = (ind % 15) / 5 + dir;
                break;
            case 3:
                max = 5;
                delta = 1;
                newd = ind % 5 + dir;
                break;
            default:
                throw new IllegalArgumentException("bla");
        }

        if (newd < 0 || newd >= max) {
            return -1;
        }

        return ind + delta * dir;
//
//        return d0 * 45 + (d1 * 15 + (d2 * 5 + d3));
    }

    private int move0(int ind, int d, int dir) {
//        2×2×2×5

//        int d0 = ind / 20;
//        ind %= 20;
//
//        int d1 = ind / 10;
//        ind %= 10;
//
//        int d2 = ind / 5;
//        int d3 = ind % 5;

        int max;
        int newd;
        int delta;
        switch (d) {
            case 0:
                max = 2;
                delta = 20;
                newd = (ind / 20) + dir;
                break;
            case 1:
                max = 2;
                delta = 10;
                newd = (ind % 20) / 10 + dir;
                break;
            case 2:
                max = 2;
                delta = 5;
                newd = (ind % 10) / 5 + dir;
                break;
            case 3:
                max = 5;
                delta = 1;
                newd = ind % 5 + dir;
                break;
            default:
                throw new IllegalArgumentException("bla");
        }

        if (newd < 0 || newd >= max) {
            return -1;
        }

        return ind + delta * dir;

//        return d0 * 20 + (d1 * 10 + (d2 * 5 + d3));
    }
}
