package solved;

import tasks.AbstractTask;
import tasks.Tester;
import utils.MyMath;
import utils.log.Logger;

//Answer : 694687
//could be easier, using http://www.wikiwand.com/en/Pick's_theorem
public class Task_504 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_504());
        Logger.close();
    }

    int m = 100;

    public void solving() {
        long ps[][] = new long[m + 1][m + 1];
        for (int a = 1; a <= m; ++a) {
            for (int b = 1; b <= m; ++b) {
                long count = 0;
                // a/b == i/db
                // db = i*b/a
                for (int i = 1; i < a; ++i) {
                    long db = i * b / a;
                    count += (b - db) - 1;
                }

                ps[a][b] = count;
            }
        }

        long res = 0;
        for (int a = 1; a <= m; ++a) {
            for (int b = 1; b <= m; ++b) {
                long ab = ps[a][b];
                for (int c = 1; c <= m; ++c) {
                    long bc = ps[b][c];
                    for (int d = 1; d <= m; ++d) {
                        long ad = ps[a][d];
                        long cd = ps[c][d];
                        long all = ab + bc + ad + cd + a + b + c + d - 3;
                        if (MyMath.isExactSquare(all)) {
                            ++res;
                        }
                    }
                }
            }
        }
        System.out.println(res);
    }
}
