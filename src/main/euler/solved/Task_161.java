package solved;

import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;

import java.util.HashMap;
import java.util.Map;

//Answer : 20574308184277971
public class Task_161 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_161());
        Logger.close();
    }

    int[][][] tr = {
            {
                    {1, 1},
                    {1, 0}
            },
            {
                    {1, 1},
                    {0, 1}
            },
            {
                    {0, 1},
                    {1, 1}
            },
            {
                    {1, 0},
                    {1, 1}
            },
            {
                    {1, 1, 1}
            },
            {
                    {1},
                    {1},
                    {1}
            }
    };

    int n = 12;
    int m = 9;

    public void solving() {
        for (int i = 0; i < w.length; i++) {
            w[i] = new HashMap<>();
        }

        long res = ways(0, 0, 0);

        System.out.println(res);
    }

    private Map<Integer, Long>[] w = new HashMap[12];
    private int newFull, newInd, newState;

    private long ways(int full, int ind, int state) {
        if (full == n) {
            return 1;
        }

        Long res = w[full].get(state);
        if (res == null) {
            res = 0L;

            for (int i = 0; i < tr.length; ++i) {
                if (tryPutTri(tr[i], full, ind, state)) {
                    res += ways(newFull, newInd, newState);
                }
            }

            w[full].put(state, res);
        }

        return res;
    }

    private boolean tryPutTri(int[][] t, int full, int ind, int state) {
        int tn = t.length;
        int tm = t[0].length;

        if (t[0][0] == 0) {
            if (ind == 0) {
                return false;
            }

            ind--;
        }

        if (full + tn > n || ind + tm > m) {
            return false;
        }

        newState = state;

        for (int i = 0; i < tn; ++i) {
            for (int j = 0; j < tm; j++) {
                if (t[i][j] == 1) {
                    if (!set(i, ind + j)) {
                        return false;
                    }
                }
            }
        }

        newInd = 0;
        newFull = full;
        TOP: for (int r = 0; r < 3; ++r) {
            for (int c = 0; c < m; ++c) {
                if ((newState & (1<<c)) == 0) {
                    newInd = c;
                    break TOP;
                }
            }
            newFull++;
            newState >>= m;
        }

        return true;
    }

    // r in 0..2
    private boolean set(int r, int c) {
        int rowMask = 1 << (r * m + c);

        if ((newState & rowMask) != 0) {
            return false;
        }

        newState |= rowMask;

        return true;
    }
}
