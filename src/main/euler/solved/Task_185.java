package solved;

import tasks.ITask;
import tasks.Tester;

import java.util.Arrays;

import static utils.MyMath.isBitSet;
import static utils.MyMath.setBit;

//Answer : 4640261571849533
public class Task_185 implements ITask {
    int cnt[] = {0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3};
    String gstr[] = {
        "2321386104303845",
        "3847439647293047",
        "4895722652190306",
        "8157356344118483",
        "3174248439465858",
        "6375711915077050",
        "6913859173121360",
        "5616185650518293",
        "4513559094146117",
        "2615250744386899",
        "6442889055042768",
        "2326509471271448",
        "5251583379644322",
        "2659862637316867",
        "5855462940810587",
        "9742855507068353",
        "4296849643607543",
        "7890971548908067",
        "8690095851526254",
        "1748270476758276",
        "3041631117224635",
        "1841236454324589"
    };

    final int n = cnt.length;
    final int m = gstr[0].length();
    int gs[][] = new int[n][m];
    boolean canbe[][] = new boolean[m][10];
    int nm[] = new int[m];

    public void solving() {
        Arrays.fill(nm, -1);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                gs[i][j] = gstr[i].charAt(j) - '0';
            }
        }

        for (int i = 0; i < m; ++i) {
            Arrays.fill(canbe[i], true);
        }

        canbe[0][0] = false;

        fill(0);
    }

    private boolean fill(int gi) {
        if (gi == n) {
            for (int i = 0; i < m; ++i) {
                if (nm[i] == -1) {
                    for (int d = 0; d < 10; ++d) {
                        if (canbe[i][d]) {
                            nm[i] = d;
                        }
                    }
                }
            }

            for (int i = 0; i < m; ++i) {
                System.out.print(nm[i]);
            }
            System.out.println("");

            return true;
        }

        return putDigits(true, gi, cnt[gi], 0);
    }

    private boolean putDigits(boolean check, int gi, int c, int beg) {
        if (check) {
            for (int i = 0; i < m; ++i) {
                if (gs[gi][i] == nm[i]) {
                    --c;
                    if (c < 0) {
                        return false;
                    }
                }
            }
        }

        if (c == 0) {
            long changed = 0;
            for (int i = 0; i < m; ++i) {
                if (nm[i] == -1 && canbe[i][gs[gi][i]]) {
                    changed = setBit(changed, i);
                    canbe[i][gs[gi][i]] = false;
                }
            }

            if (fill(gi + 1)) {
                return true;
            }

            for (int i = 0; i < m; ++i) {
                if (isBitSet(changed, i)) {
                    canbe[i][gs[gi][i]] = true;
                }
            }

            return false;
        }

        for (int i = beg; i < m; ++i) {
            if (nm[i] == -1 && canbe[i][gs[gi][i]]) {
                nm[i] = gs[gi][i];
                if (putDigits(false, gi, c - 1, i + 1)) {
                    return true;
                }
                nm[i] = -1;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Tester.test(new Task_185());
    }
}
