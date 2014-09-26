package solved;

import tasks.ITask;

import java.util.TreeSet;

import static utils.MyMath.getPrimeDivisors;

//Answer : 14125034
public class Task_127 implements ITask {
    int MAX = 100000;

    TreeSet<Long> pds[] = new TreeSet[MAX+1];
    public void solving() {
        int cnt = 0;
        long sum = 0;
        pds[1] = new TreeSet<Long>();
        for (int c = 2; c < MAX; ++c ) {
            pds[c] = new TreeSet<Long>(getPrimeDivisors(c));
            if (pds[c].first()==c) continue;

            int p = 1;
            for (long pdn : pds[c]) {
                p *= pdn;
            }

            HERE:
            for (int a = 1; a < c/2; ++a) {
                int b = c - a;
                long p1 = p;
                for (long pdn : pds[a]) {
                    if (p1 >= c) break;
                    if (!pds[c].contains(pdn)) p1 *= pdn;
                    else continue HERE;
                }
                for (long pdn : pds[b]) {
                    if (p1 >= c) break;
                    if (!pds[c].contains(pdn) && !pds[a].contains(pdn)) p1 *= pdn;
                    else continue HERE;
                }
                if (p1 < c) {
                    ++cnt;
                    sum += c;
                    System.out.println(cnt + ": " + c + "; Sum = " + sum);
                }
            }
        }
    }
}
