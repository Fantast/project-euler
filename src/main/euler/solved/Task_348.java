package solved;

import tasks.AbstractTask;
import tasks.Tester;
import utils.OtherUtils;
import utils.log.Logger;

import java.util.HashMap;
import java.util.Map;

//Answer : 1004195061
public class Task_348 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_348());
        Logger.close();
    }

    int m3 = 3000;
    int m2 = 30000;
    long a2[] = new long[m2 + 1];
    long a3[] = new long[m3 + 1];
    public final Map<Long, Integer> all = new HashMap();

    public void solving() {
        for (int a = 1; a <= m3; a++) {
            a3[a] = (long) a * (long) a * (long) a;
        }
        for (int a = 1; a <= m2; a++) {
            a2[a] = (long) a * (long) a;
        }

        for (int a = 1; a <= m3; ++a) {
            long aa = a3[a];
            for (int b = 1; b <= m2; ++b) {
                long p = aa + a2[b];
                Integer c = all.get(p);
                if (c != null) {
                    all.put(p, c + 1);
                } else if (isPalindrom(p)) {
                    all.put(p, 1);
                }
            }
        }

        long res = 0;
        for (Map.Entry<Long, Integer> e : all.entrySet()) {
            if (e.getValue() == 4) {
                res += e.getKey();
                System.out.println(e.getKey() + ": " + e.getValue());
            }
        }
        System.out.println(res);
    }

    private boolean isPalindrom(long p) {
        String s = Long.toString(p);
        int sn = s.length() /2;
        for (int i = 0, ie = s.length() - 1; i < ie; ++i, --ie) {
            if (s.charAt(i) != s.charAt(ie)) {
                return false;
            }
        }
        return OtherUtils.isPalindrom(s);
    }
}
