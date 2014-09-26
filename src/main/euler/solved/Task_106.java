package solved;

import tasks.ITask;
import tasks.Tester;
import utils.pairs.IntPair;

import java.util.Arrays;
import java.util.TreeSet;

import static utils.MyMath.pow10;

//Answer : 21384
public class Task_106 implements ITask {
    final int n = 12;

    int vals[] = new int[n];

    boolean used[] = new boolean[n];

    int d1[] = new int[n];
    int d2[] = new int[n];

    int res = 0;
    public void solving() {
        for (int i = 0; i < n; ++i) {
            vals[i] = i;
        }

        checkSum(0, 0);
        System.out.println(res);
    }

    private void checkSum(int beg, int cnt) {
        if (cnt > 1 && needToCheck(cnt)) {
            ++res;
        }
        for (int i = beg; i < n; ++i) {
            if (cnt==0) {
                System.out.println("Progress: " + i);
            }
            if (used[i]) continue;
            used[i] = true;
            d1[cnt] = i+1;
            for (int j = 0; j < n; ++j) {
                if (used[j]) continue;
                used[j] = true;
                d2[cnt] = j+1;

                checkSum(i+1, cnt+1);

                used[j] = false;
            }
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        Tester.test(new Task_106());
    }

    int d11[] = new int[n];
    int d22[] = new int[n];
    private boolean needToCheck(int cnt) {
        System.arraycopy(d1, 0, d11, 0, cnt);
        System.arraycopy(d2, 0, d22, 0, cnt);

        Arrays.sort(d11, 0, cnt);
        Arrays.sort(d22, 0, cnt);

        if (d11[0] > d22[0]) {
            if (alreadyChecked(d11, d22, cnt)) return false;
        } else {
            if (alreadyChecked(d22, d11, cnt)) return false;
        }

        int cmp = 0;
        for (int i = 0; i < cnt; ++i) {
            if (d11[i] > d22[i]) {
                if (cmp == 0) cmp = 1;
                else if (cmp != 1) return true;
            } else {
                if (cmp == 0) cmp = 2;
                else if (cmp != 2) return true;
            }
        }
        return false;
    }

    TreeSet<IntPair> all = new TreeSet<IntPair>();
    private boolean alreadyChecked(int[] d11, int[] d22, int cnt) {
        IntPair np = new IntPair(hash(d11,cnt), hash(d22,cnt));
        if (!all.contains(np)) {
            all.add(np);
            return false;
        } else {
            return true;
        }
    }

    private int hash(int d[], int cnt) {
        int hash = 0;
        for (int i = 0; i < cnt; ++i) {
            hash += d[i] * pow10[i];
        }
        return hash;
    }
}
