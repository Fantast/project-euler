package solved;

import tasks.ITask;

import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

//Answer : 2325629
public class Task_186 implements ITask {
    int end[] = new int[56];

    int s[] = new int[4000000];
    boolean isf[] = new boolean[1000000];

    private TreeSet<Integer> fr[] = new TreeSet[1000000];
    private static final int PM = 524287;

    public void solving() {
        for (int i = 0; i < 1000000; ++i) fr[i] = new TreeSet<Integer>();

        for (int i = 1; i <= 55; ++i) {
            s[i] = end[i] = (int) ((100003L - 200003L*i + 300007L*i*i*i)%1000000L);
        }

        int beg;
        for (beg = 56; ;++beg) {
            next();
            s[beg] = end[55];

            if (s[beg]==PM) {
                System.out.println(beg);
                break;
            }
        }

        int calls = 0;

        for (int i = 1; i <= beg/2; ++i) {
            int f1 = s[2*i-1];
            int f2 = s[2*i];

            if (f1 == f2) continue;
            calls++;

            fr[f1].add(f2);
            fr[f2].add(f1);
        }

        int cnt = markAll(end[55]);
        for (;;) {
            next();
            next();

            int f1 = end[54];
            int f2 = end[55];

            if (f1 == f2) continue;
            ++calls;
            fr[f1].add(f2);
            fr[f2].add(f1);

            if (isf[f1] && !isf[f2]) {
                cnt += markAll(f2);
            } else if (!isf[f1] && isf[f2]) {
                cnt += markAll(f1);
            }

            if (cnt >= 990000) break;
        }

        System.out.println(calls);
    }

    private int markAll(int k) {
        if (isf[k]) return 0;
        int res = 1;
        isf[k] = true;

        Queue<Integer> q = new LinkedList<Integer>();
        q.add(k);
        while (!q.isEmpty()) {
            Integer p = q.poll();
            for (int f : fr[p]) {
                if (!isf[f]) {
                    isf[f] = true;
                    ++res;
                    q.add(f);
                }
            }
        }

        return res;
    }

    private void next() {
        int ns = (end[56-24] + end[56-55])%1000000;

        for (int j = 1; j <= 54; ++j) {
            end[j] = end[j+1];
        }
        end[55] = ns;
    }
}