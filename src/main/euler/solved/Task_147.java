package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

//Answer : 846910284
public class Task_147 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_147());
        Logger.close();
    }

    int A = 43;
    int B = 47;

    public void solving() {
        long res = 0;
        for (int a = 1; a <= A; ++a) {
            for (int b = a; b <= B; ++b) {
                long f = find(a, b);
                res += f;

                if (b <= A && a != b) {
                    res += f;
                }
            }
        }
        System.out.println(res);
    }

    private long find(int a, int b) {
        return findRect(a, b) + findDiag(a, b);
    }

    long rect[][] = new long[A+1][B+1];
    private long findRect(int a, int b) {
        if (a > b) return findRect(b, a);

        if (rect[a][b] == 0) {
            return rect[a][b] = b == 1 ? 1 : findRect(a, b - 1) + a*(a + 1)/2 * b;
        }
        return rect[a][b];
    }

    long diag[][] = new long[A+1][B+1];
    private long findDiag(int a, int b) {
        if (a > b) return findDiag(b, a);

        if (diag[a][b] == 0) {
            if (a == 1 && b == 1) return diag[a][b] = 0;

            long res = findDiag(a, b-1);

            for (int i = 1; i < a; ++i) {
                res += 4*i*(a-i);
            }

            for (int i = 0; i < a; ++i) {
                res += (2*i + 1)*(2*(a - i) - 1) - (a==b ? 1 : 0);
            }

            diag[a][b] = res;
        }
        return diag[a][b];
    }
}
