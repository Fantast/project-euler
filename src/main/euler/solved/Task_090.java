package solved;

import tasks.ITask;
import tasks.Tester;

import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;

//Answer : 1217
public class Task_090 implements ITask {

    public void solving() {
        long count = 0;
        for (int a = 0; a < 10; ++a) {
            for (int b = a + 1; b < 10; ++b) {
                for (int c = b + 1; c < 10; ++c) {
                    for (int d = c + 1; d < 10; ++d) {
                        for (int e = d + 1; e < 10; ++e) {
                            for (int f = e + 1; f < 10; ++f) {

                                for (int a1 = 0; a1 < 10; ++a1) {
                                    for (int b1 = a1 + 1; b1 < 10; ++b1) {
                                        for (int c1 = b1 + 1; c1 < 10; ++c1) {
                                            for (int d1 = c1 + 1; d1 < 10; ++d1) {
                                                for (int e1 = d1 + 1; e1 < 10; ++e1) {
                                                    for (int f1 = e1 + 1; f1 < 10; ++f1) {
                                                        if (isCorrect(a, b, c, d, e, f, a1, b1, c1, d1, e1, f1)) {
                                                            ++count;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println(count);
    }

    public static void main(String[] args) {
        Tester.test(new Task_090());
    }

    private boolean isCorrect(int a, int b, int c, int d, int e, int f,
            int a1, int b1, int c1, int d1, int e1, int f1) {

        TreeSet<Integer> cb1 = new TreeSet<Integer>(Arrays.asList(a, b, c, d, e, f));
        TreeSet<Integer> cb2 = new TreeSet<Integer>(Arrays.asList(a1, b1, c1, d1, e1, f1));

        Iterator<Integer> it = cb2.iterator();
        boolean isOk = false;
        for (int nc1 : cb1) {
            int nc2 = it.next();
            if (nc2 < nc1) {
                break;
            }
            if (nc2 > nc1) {
                isOk = true;
                break;
            }
        }
        if (!isOk) {
            return false;
        }

        for (int n : new int[]{1, 4, 9, 16, 25, 36, 49, 64, 81}) {
            int r1 = n / 10;
            int r2 = n % 10;

            int r11 = r1 == 6 ? 9 : r1 == 9 ? 6 : r1;
            int r22 = r2 == 6 ? 9 : r2 == 9 ? 6 : r2;

            if ((cb1.contains(r1) || cb1.contains(r11)) &&
                    (cb2.contains(r2) || cb2.contains(r22))) {
                continue;
            }

            if ((cb2.contains(r1) || cb2.contains(r11)) &&
                    (cb1.contains(r2) || cb1.contains(r22))) {
                continue;
            }

            return false;
        }

        return true;
    }
}
