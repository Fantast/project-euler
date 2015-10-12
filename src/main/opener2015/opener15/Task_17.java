package opener15;

import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

//Answer : 1033411
public class Task_17 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_17());
        Logger.close();
    }

    int res = 0;
    int max = 13 * 2;
    boolean up[] = new boolean[100];

    public void solving() {
        long a[] = new long[] {
                1, 1, 2, 5, 14, 42, 132, 429, 1430, 4862, 16796, 58786, 208012, 742900, 2674440, 9694845, 35357670, 129644790, 477638700,
                1767263190, 6564120420L, 24466267020L, 91482563640L
        };

        long res = 0;
        for (int i = 1; i <= 13; ++i) {
            res += a[i];
        }

//        generate(0, 0);
        System.out.println(res);
    }

    private void generate(int elevation, int len) {
        if (len == max) {
            return;
        }

        if (elevation == 0 && len > 1) {
            res++;
//            output(len);
        }

//        if (elevation > 0) {
//            up[len] = false;
//            generate(elevation - 1, len + 1);
//        } else {
//            up[len] = true;
//            generate(elevation + 1, len + 1);
//        }
//
//        if (elevation != 0  && elevation < max - len) {
//            up[len] = true;
//            generate(elevation + 1, len + 1);
//        }

        if (elevation == 0) {
            up[len] = true;
            generate(elevation + 1, len + 1);
        } else if (elevation == max - len) {
            up[len] = false;
            generate(elevation - 1, len + 1);
        } else {
            up[len] = true;
            generate(elevation + 1, len + 1);
            up[len] = false;
            generate(elevation - 1, len + 1);
        }
    }

    private void output(int len) {
        System.out.println("--------------");
        char a [][] = new char[len][len];
        int r = len - 1;
        int mn = 0;
        for (int i = 0; i < len; ++i) {
            if (up[i]) {
                a[r][i] = '/';
                r--;
            } else {
                a[r][i] = '\\';
                r++;
            }
            mn = Math.min(mn, r);
        }

        for (int y = mn; y < len; y++) {
            for (int x = 0; x < len; ++x) {
                System.out.print(a[y][x]);
            }
            System.out.println();
        }
    }
}
