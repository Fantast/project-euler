package solved;

import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;

import java.util.HashMap;
import java.util.Map;

//Answer : 10834893628237824
public class Task_189 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_189());
        Logger.close();
    }

    int rc = 8;
    public void solving() {
        for (int i = 0; i < ways.length; i++) {
            ways[i] = new HashMap<>();
        }
        long res = count(0, 0);
        System.out.println(res);
    }

    final Map<Integer, Long> ways[] = new Map[9];

    public long count(int row, int m) {
        if (row == rc) {
            return 1;
        }

        Map<Integer, Long> wr = ways[row];
        Long r = wr.get(m);
        if (r != null) {
            return r;
        }

        long res = iterate(row, 0, -1, m, 0);

        wr.put(m, res);

        return res;
    }

    private long iterate(int row, int col, int prevVal, int m, int nm) {
        if (col > row * 2) {
            return count(row + 1, nm);
        }

        long res = 0;
        int down = ((col&1) == 1) ? get(m, col - 1) : -1;
        for (int val = 0; val < 3; ++val) {
            if (val != down && val != prevVal) {
                res += iterate(row, col + 1, val, m, set(nm, col, val));
            }
        }

        return res;
    }

    private int get(int m, int ind) {
        return (m >> (ind << 1)) & 0b11;
    }

    private int set(int m, int ind, int val) {
        return m | (val << (ind << 1));
    }
}
