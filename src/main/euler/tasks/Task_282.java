package tasks;

import utils.log.Logger;
import utils.pairs.IntPair;

import java.util.HashSet;

//Answer :
public class Task_282 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_282());
        Logger.close();
    }

    private static final int MOD = 1475789056;
    private int resCycleLen;
    private int resCycleBeg;
    int resRems[];

    private int twoCycleLen;
    private int twoCycleBeg;
    int twoRems[];

    public void solving() {
        fillPowTwo();

//        OtherUtils.deepFillInt(a, -1);

//        System.out.println(a(3, 4));

//        long sum = 0;
//        for (int n = 0; n <= 6; ++n) {
//            sum = (sum + (long)a(n, n)) % MOD;
//        }
//        System.out.println(sum);

        System.out.println(resPowTwo(11));
    }

    private void fillPowTwo() {
        IntPair rp = findTwoCycle(MOD);
        IntPair tp = findTwoCycle(rp.b);

        resCycleBeg = rp.a;
        resCycleLen = rp.b;
        resRems = new int[resCycleLen];

        twoCycleBeg = tp.a;
        twoCycleLen = tp.b;
        twoRems = new int[twoCycleLen];

        fillPowTwoArray(resRems, MOD, resCycleBeg);
        fillPowTwoArray(twoRems, resCycleLen, twoCycleBeg);
    }

    private void fillPowTwoArray(int rems[], int mod, int cycleBeg) {
        int p = 1 << cycleBeg;
        rems[0] = p%mod;
        for (int i = 1; i < rems.length; ++i) {
            rems[i] = (int) ((rems[i-1]*2L)%mod);
        }
    }

    private IntPair findTwoCycle(int mod) {
        HashSet<Integer> mods = new HashSet<Integer>();
        int n = 1;
        int cycleBeg = 0;
        while (!mods.contains(n)) {
            mods.add(n);
            n = (int) ((2L * n) % mod);
            ++cycleBeg;
        }

        int n2 = (int) ((2L * n) % mod);
        int cycleLen = 1;
        while (n2 != n) {
            ++cycleLen;
            n2 = (int) ((2L * n2) % mod);
        }
        cycleBeg -= cycleLen;

        System.out.println(cycleBeg);
        System.out.println(cycleLen);
        System.out.println("------------");

        return new IntPair(cycleBeg, cycleLen);
    }

    int a[][] = new int[7][30000];

    private int a(int m, int n) {
        if (a[m][n] != -1) {
            return a[m][n];
        }

        int res = 0;

        if (m == 0) {
            res = (n + 1) % MOD;
        } else if (m == 1) {
            res = (n + 2) % MOD;
        } else if (m == 2) {
            res = (2 * n + 3) % MOD;
        } else if (m == 3) {
            return (resPowTwo(n + 3) + MOD - 3) % MOD;
        } else if (n == 0) {
            res = a(m - 1, 1);
        } else {
            res = a(m - 1, a(m, n - 1));
        }

        return a[m][n] = res;
    }

    private int resPowTwo(int pow) {
        if (pow <= resCycleBeg) {
            return 1 << pow;
        }

        pow = (pow - resCycleBeg) % resCycleLen;

        return resRems[pow];
    }

    private int twoPowTwo(int pow) {
        if (pow <= twoCycleBeg) {
            return 1 << pow;
        }

        pow = (pow - twoCycleBeg) % twoCycleLen;

        return twoRems[pow];
    }

    private int hyper2(int lvl, int p, boolean isResMod) {
        if (p == 0) return 1;
        if (p == 1) return 2;
        if (p == 2) return 4;

        if (lvl == 1) {
            return isResMod ? resPowTwo(p) : twoPowTwo(p);
        }


        if (!isResMod) {
            int res = hyper2(lvl - 1, 2, false);
            for (int i = 2; i < p; ++i) {
                res = hyper2(lvl - 1, res, false);
            }

            return res;
        } else {
            int res = hyper2(lvl - 1, 2, false); // ==4
            for (int i = 2; i < p; ++i) {
                res = hyper2(lvl - 1, res, false);
            }

            return res;
        }
    }
}
