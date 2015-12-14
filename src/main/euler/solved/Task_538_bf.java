package solved;

import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;

import java.math.BigInteger;
import java.util.HashMap;

import static utils.MyMath.bitCount;
import static utils.MyMath.pow;

//Answer : 22472871503401097
public class Task_538_bf extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_538_bf());
        Logger.close();
    }

    public int LIM = 150;
//    public int LIM = 3000000;

    long u[] = new long[2000];
    int uc[] = new int[2000];
    int count;

    public void solving() {
        long s = 0;

        HashMap<Long, Integer> indexes = new HashMap<>();
        for (int n = 1; n <= LIM; ++n) {
            if (progress1000(n)) {
                System.out.println("  " + count);
            }

            long un = U(n);
            Integer index = indexes.get(un);
            if (index == null) {
                u[count] = un;
                uc[count] = 1;
                indexes.put(un, count);

                find(count, un, 1);

                ++count;
            } else if (index < 4) {
                uc[index]++;
                find(index, un, uc[index]);
            }

            s += bestP;
        }
        System.out.println(s);
    }

    void find(int ind, long un, int cc) {
        if (cc == 4) {
            quad(un, un, un, un);
        } else if (cc == 3) {
            for (int i = 0; i < count; ++i) {
                if (i != ind) {
                    quad(un, un, un, u[i]);
                }
            }
        } else if (cc == 2) {
            for (int i = 0; i < count; ++i) {
                if (i != ind) {
                    long ui = u[i];
                    if (uc[i] > 1) {
                        quad(un, ui, un, ui);
                    }

                    for (int j = i + 1; j < count; ++j) {
                        if (j != ind) {
                            quad(un, un, ui, u[j]);
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < count; ++i) {
                if (i != ind) {
                    long ui = u[i];
                    long uci = uc[i];
                    if (uci > 2) {
                        quad(ui, ui, ui, un);
                    }

                    if (uci > 1) {
                        for (int j = 0; j < count; ++j) {
                            if (j != i && j != ind) {
                                quad(ui, ui, un, u[j]);
                            }
                        }
                    }

                    for (int j = i + 1; j < count; ++j) {
                        if (j != ind) {
                            for (int k = j + 1; k < count; ++k) {
                                if (k != ind) {
                                    quad(un, ui, u[j], u[k]);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    BigInteger bestA = BigInteger.ZERO;
    long bestP = 0;

    private void quad(long a, long b, long c, long d) {
        long P = a + b + c + d;

        long mx = max(a, b, c, d);
        if (P - mx > mx) {
            BigInteger bP = bi(P);
            BigInteger A = bP.subtract(bi(2*a))
                    .multiply(bP.subtract(bi(2*b)))
                    .multiply(bP.subtract(bi(2*c)))
                    .multiply(bP.subtract(bi(2*d)));
//            long A = (P - 2 * a) * (P - 2 * b) * (P - 2 * c) * (P - 2 * d);
            int cmp = A.compareTo(bestA);
            if (cmp > 0) {
                bestA = A;
                bestP = P;
            } else if (cmp == 0 && P > bestP) {
                bestP = P;
            }
        }
    }

    private long U(int n) {
        return pow(2, bitCount(3 * n)) + pow(3, bitCount(2 * n)) + bitCount(n + 1);
    }
}
