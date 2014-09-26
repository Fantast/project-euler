package solved;

import tasks.ITask;
import tasks.Tester;

//Answer : 20313839404245
public class Task_103 implements ITask {

    int n = 7;
    private int MINSUM = 120;
    private int FIRST = 20;
    int p[] = new int[n];
    int sm[] = new int[n + 1];

    public void solving() {
        for (int sum = MINSUM;; ++sum) {
            if (find(0, FIRST, sum)) {
                System.out.println("Sum: " + sum);
                break;
            }
        }
        for (int pn : p) {
            System.out.print(pn);
        }
    }

    private boolean find(int ind, int min, int sum) {
        if (ind == n) {
            return sum == 0 && checkSum(n, 0, 0);
        }

        for (int i = min; (i + 1) * (n - ind - 1) <= (sum - i); ++i) {
            p[ind] = i;
            sm[ind + 1] = sm[ind] + i;
            if (check(i, ind + 1)) {
                if (find(ind + 1, i + 1, sum - i)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean check(int d, int cnt) {
        //check 2nd propety
        for (int c = 2; c <= cnt; ++c) {
            int smmax = sm[cnt] - sm[cnt - (c - 1)];
            if (sm[c] <= smmax) {
                return false;
            }
        }
        return true;
    }
    boolean used[] = new boolean[n];

    private boolean checkSum(int cnt, int s1, int s2) {
        if (s1 != 0 && s1 == s2) {
            return false;
        }

        for (int i = 0; i < cnt; ++i) {
            if (used[i]) continue;
            used[i] = true;
            for (int j = 0; j < cnt; ++j) {
                if (used[j])continue;
                used[j] = true;
                if (!checkSum(cnt, s1 + p[i], s2 + p[j])) {
                    used[i] = false;
                    used[j] = false;
                    return false;
                }
                used[j] = false;
            }
            used[i] = false;
        }

        return true;
    }

    public static void main(String[] args) {
        Tester.test(new Task_103());
    }
}
