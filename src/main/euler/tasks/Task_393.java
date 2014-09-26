package tasks;

import utils.MyMath;
import utils.log.Logger;

import java.util.HashMap;

//Answer :
public class Task_393 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_393());
        Logger.close();
    }


    int LEFT = 0;
    int RIGHT = 1;
    int UP = 2;
    int DOWN = 3;

    String[] dirs = new String[] { "← ", "→ ", "↑ ", "↓ "};

    int n = 8;
    long n2 = n*n;
//    long finalMask = (1L << n2) - 1;
    long finalMask = (-1L << n2);

    int usedDir[][] = new int[n][n];
    long pow2[] = new long[30];

    public void solving() {
        for (int i = 1; i < pow2.length; ++i) {
            pow2[i] = 1L << (long)i;
        }

        usedDir[0][0] = RIGHT;
        System.out.println(dfs(1, 1, 0, 0, 0, 1, true));
    }

    private long dfs(long mask, int cycleCount, int br, int bc, int r, int c, boolean startOfCycle) {
        if (startOfCycle) {
            Long res = getCache(mask);
            if (res != null) {
                return res;
            }
        }

        if (br == r && bc == c) {
            if (mask == finalMask) {
//                output();
//                System.out.println(cycleCount);

                return pow2[cycleCount];
            }
            int nr = -1;
            int nc = -1;
            TOP: for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!isBitSet(mask, i, j)) {
                        nr = i;
                        nc = j;
                        break TOP;
                    }
                }
            }

            //todo: cut (n-1) case earlier
            if (nr == -1 || nr == n-1 || nc == n-1 || isBitSet(mask, nr, nc+1)) {
                return 0;
            }

            usedDir[nr][nc] = RIGHT;
            return putCache(mask, dfs(setBit(mask, nr, nc), cycleCount + 1, nr, nc, nr, nc + 1, true));
        } else {
            long nMask = setBit(mask, r, c);

            long result = 0;

            if (cango(mask, br, bc, r, c+1, startOfCycle)) {
                usedDir[r][c] = RIGHT;
                result += dfs(nMask, cycleCount, br, bc, r, c+1, false);
            }
            if (cango(mask, br, bc, r+1, c, startOfCycle)) {
                usedDir[r][c] = DOWN;
                result += dfs(nMask, cycleCount, br, bc, r+1, c, false);
            }
            if (cango(mask, br, bc, r, c-1, startOfCycle)) {
                usedDir[r][c] = LEFT;
                result += dfs(nMask, cycleCount, br, bc, r, c-1, false);
            }
            if (cango(mask, br, bc, r-1, c, startOfCycle)) {
                usedDir[r][c] = UP;
                result += dfs(nMask, cycleCount, br, bc, r-1, c, false);
            }
            return result;
        }
    }

    private void output() {
        System.out.println("---------------------");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(dirs[usedDir[i][j]]);
            }
            System.out.println();
        }
    }

    HashMap<Long, Long> cache = new HashMap<Long, Long>();
    public Long getCache(long mask) {
        return cache.get(mask);
    }

    public long putCache(long mask, long value) {
        cache.put(mask, value);
        return value;
    }

    public boolean isBitSet(long mask, int r, int c) {
        return MyMath.isBitSet(mask, r * n + c);
    }

    public long setBit(long mask, int r, int c) {
        return MyMath.setBit(mask, r*n + c);
    }

    public long unsetBit(long mask, int r, int c) {
        return MyMath.unSetBit(mask, r * n + c);
    }

    private boolean cango(long mask, int br, int bc, int r, int c, boolean startOfCycle) {
        if (r < 0 || c < 0 || r >= n || c >= n) {
            return false;
        }
        if (!isBitSet(mask, r, c)) {
            return true;
        }

        if (r == br && c == bc && !startOfCycle) {
            return true;
        }

        return false;
    }
}
