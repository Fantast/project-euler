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
public class Task_32 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_32());
        Logger.close();
    }

    public void solving() {
        System.out.println(find(new BitSet(90)));
    }

    Map<BitSet, Long> ff = new HashMap<>();

    int count = 0;
    long find(BitSet state) {
        ++count;
        if (count < 10) {
            System.out.println("Current: " + count);
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
        if (ind >= 90) {
            System.out.println("Got some...");
            return 1L;
        }

        state.set(ind);

        long res = 0;
        for (int pos = 0; pos < 3; ++pos) {
            res += go(state, ind, ind, 0, pos);
        }

        state.clear(ind);

        return res;
    }

    private Long go(BitSet state, int startInd, int endInd, int isBent, int pos) {
        if (isBent == 0b1111) {
            return find(state);
        }

        boolean toEnd = pos > 0;
        int ind = toEnd ? endInd : startInd;

        long res = 0;
        for (int d = 0; d < 4; ++d) {
            if (!isBitSet(isBent, d)) {
                int newBent = setBit(isBent, d);
                for (int dir = -1; dir <= 1; dir += 2) {
                    int newInd = move(ind, d, dir);
                    if (newInd != -1 && !state.get(newInd)) {
                        state.set(newInd);
                        if (toEnd) {
                            res += go(state, startInd, newInd, newBent, pos - 1);
                        } else {
                            res += go(state, newInd, endInd, newBent, pos - 1);
                        }
                        state.clear(newInd);
                    }
                }
            }
        }

        return res;
    }

    private int move(int ind, int d, int dir) {
//        2×3×3×5

        int d0 = ind / 45;
        ind %= 45;

        int d1 = ind / 15;
        ind %= 15;

        int d2 = ind / 5;
        int d3 = ind % 5;

        int max;
        int newd;
        switch (d) {
            case 0:
                max = 2;
                newd = d0 = d0 + dir;
                break;
            case 1:
                max = 3;
                newd = d1 = d1 + dir;
                break;
            case 2:
                max = 3;
                newd = d2 = d2 + dir;
                break;
            case 3:
                max = 5;
                newd = d3 = d3 + dir;
                break;
            default:
                throw new IllegalArgumentException("bla");
        }

        if (newd < 0 || newd >= max) {
            return -1;
        }

        return d0 * 45 + (d1 * 15 + (d2 * 5 + d3));
    }
}
