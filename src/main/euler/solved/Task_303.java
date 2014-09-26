package solved;

import tasks.ITask;
import tasks.Tester;
import utils.MyMath;
import utils.log.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//Answer : 1111981904675169
public class Task_303 implements ITask {

    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_303());
        Logger.close();
    }
    public static final int n = 10000;
    List<Integer> all = new ArrayList<Integer>(n);

    public void solving9999() {
        long res = 648549117391L;
        for (long j = Long.parseLong("1112222222222220", 3); ; ++j) {
            long tj = Long.parseLong(Long.toString(j, 3), 10);
            if (tj % 9999 == 0) {
                res += tj / 9999;
                System.out.println(zeros(9999) + ": " + 10000 + ": " + tj + ": " + res);
                break;
            }
        }
        System.out.println(res);
    }

    public void solving() {
        for (int i = 1; i <= n; ++i) {
            all.add(i);
        }

        long res = 0;
        for (int j = 1; !all.isEmpty(); ++j) {
            long tj = Long.parseLong(Long.toString(j, 3), 10);
            for (Iterator<Integer> it = all.iterator(); it.hasNext();) {
                int i = it.next();
                if (tj % i == 0) {
                    res += tj / i;
                    it.remove();
                    System.out.println(zeros(i) + ": " + (n - all.size()) + ": " + tj + ": " + res);
                }
            }
        }
        System.out.println(res);
    }
    String zeros[] = new String[]{"", "0", "00", "000", "0000"};

    private String zeros(int i) {
        return zeros[5 - MyMath.numberOfDigits(i)] + i;
    }

    private boolean valid(long n) {
        while (n != 0) {
            if (n % 10 > 2) {
                return false;
            }
            n /= 10;
        }

        return true;
    }
}
