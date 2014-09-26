package solved;

import tasks.ITask;
import tasks.Tester;

import java.util.TreeSet;

import static utils.OtherUtils.isPalindrom;

//Answer : 2906969179
public class Task_125 implements ITask {

    long sqsum[] = new long[10001];
    private long LIM = 100000000;

    public void solving() {
        int n = sqsum.length;
        for (int i = 1; i < n; ++i) {
            sqsum[i] = sqsum[i-1] + i*i;
        }

        TreeSet<Long> all = new TreeSet<Long>();
        long sum = 0;
        for (int i = 1; i < n; ++i) {
            for (int j = i+1; j < n; ++j) {
                long nb = sqsum[j] - sqsum[i-1];
                if (nb < LIM && !all.contains(nb) && isPalindrom(String.valueOf(nb))) {
                    all.add(nb);
                    sum += nb;
                }
            }
        }
        System.out.println(sum);
    }

    public static void main(String[] args) {
        Tester.test(new Task_125());
    }
}
