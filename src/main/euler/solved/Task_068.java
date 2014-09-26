package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

//Answer : 6531031914842725
public class Task_068 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_068());
        Logger.close();
    }

    private static final int n = 5;
    private static final int n2 = 10;
    int ring[] = new int[11];
    boolean used[] = new boolean[11];

    private String res = "";
    int endInds[] = new int[] {1, 3, 9, 10, 6};
    int triples[][] = {
            {1, 2, 5},
            {3, 5, 8},
            {9, 8, 7},
            {10, 7, 4},
            {6, 4, 2}
    };

    public void solving() {
        ring[1] = 10;
        used[10] = true;
        fill(2);

        System.out.println( res );
    }

    private void fill(int ind) {
        if (ind == n2+1) {
            if (check()) {
                String ns = makeString();
                if (ns.compareToIgnoreCase(res) > 0) res = ns;
            }
            return;
        }

        for (int i = 1; i <= n2; ++i) {
            if (!used[i]) {
                used[i] = true;
                ring[ind] = i;
                fill(ind + 1);
                used[i] = false;
            }
        }
    }

    private String makeString() {
        int mi = 0;
        for (int i = 1; i < endInds.length; ++i) {
            if (ring[endInds[mi]] > ring[endInds[i]]) mi = i;
        }

        String s = "";
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < 3; ++j) {
                s += ring[ triples[(mi+i)%n][j] ];
            }
        }

        return s;
    }

    private boolean check() {
        int sm = -1;
        for (int i = 0; i < n; ++i) {
            int smn = 0;
            for (int j = 0; j < 3; ++j) {
                smn += ring[ triples[i][j] ];
            }
            if (sm == -1) sm = smn;
            else if (sm != smn) return false;
        }
        return true;
    }
}