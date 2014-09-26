package solved;

import tasks.ITask;
import tasks.Tester;
import utils.FractionPoint;

import java.util.HashSet;

import static utils.Geometry.getTrueIntersection;

//Answer : 2868868
public class Task_165 implements ITask {
    int n = 5000;
    long s[] = new long[4 * n + 1];
    int t[] = new int[4 * n];

    public void solving() {
        s[0] = 290797;
        for (int i = 1; i < s.length; ++i) {
            s[i] = (s[i - 1] * s[i - 1]) % 50515093L;
            t[i-1] = (int) (s[i] % 500);
        }


        HashSet<FractionPoint> all = new HashSet<FractionPoint>();
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                FractionPoint p = getTrueIntersection(
                        t[4 * i], t[4 * i + 1], t[4 * i + 2], t[4 * i + 3],
                        t[4 * j], t[4 * j + 1], t[4 * j + 2], t[4 * j + 3]);

                if (p != null) {
                    all.add(p);
                }
            }
        }

        System.out.println(all.size());

    }

    public static void main(String[] args) {
        Tester.test(new Task_165());
    }
}