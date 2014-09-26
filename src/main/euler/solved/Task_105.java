package solved;

import tasks.ITask;
import utils.FileUtils;
import utils.STLUtils;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Long.parseLong;

//Answer : 73702

public class Task_105 implements ITask {
    public void solving() {
        long res = 0;
        ArrayList<String> sStr = FileUtils.readLines("files/sets.txt");
        for (String setStr : sStr) {
            String spl[] = setStr.split(",");
            long set[] = new long[spl.length];
            for (int i = 0; i < spl.length; ++i) {
                set[i] = parseLong(spl[i]);
            }

            boolean isOk = isSpecialSumSet(set);
            if (isOk) {
                res += STLUtils.accumulate(set);
            }

            if (isOk) {
                Arrays.sort(set);
                System.out.println(Arrays.toString(set));
            }
        }

        System.out.println("Result: " + res);
    }

    private boolean isSpecialSumSet(long[] set) {
        int n = set.length;
        int cnt = 1 << n;

        long S[] = new long[cnt];
        int pow[] = new int[cnt];
        for (int i = 1; i < cnt; ++i) {
            for (int j = 0; j < n; ++j) {
                if (isBitSet(i, j)) {
                    S[i] += set[j];
                }
            }
            pow[i] = Long.bitCount(i);
        }

        for (int i = 0; i < cnt - 1; ++i) {
            for (int j = i + 1; j < cnt; ++j) {
                if (i + j != (i | j)) continue;
                if (S[i] == S[j]) return false;
                if (pow[i] > pow[j] && S[i] < S[j]) return false;
                if (pow[i] < pow[j] && S[i] > S[j]) return false;
            }
        }

        return true;
    }

    private boolean isBitSet(int n, int i) {
        return (n & (1 << i)) != 0;
    }
}
