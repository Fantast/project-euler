package opener;

import tasks.ITask;
import tasks.Tester;
import utils.MyMath;
import utils.OtherUtils;
import utils.log.Logger;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;

//Answer :
public class Task_31 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_31());
        Logger.close();
    }

    Set<Integer> primes = new HashSet<>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251));
    boolean isprime[] = new boolean[258];
    int group[] = new int[15];
    int grcnt = 0;

    MathContext mc = new MathContext(100);

    BigDecimal b12 = divide(bd(1), bd(2));
    BigDecimal b16 = divide(bd(1), bd(6));
    BigDecimal sum27div6 = divide(bd(2 + 3 + 4 + 5 + 6 + 7), bd(6));
    BigDecimal sum16div6 = divide(bd(1 + 2 + 3 + 4 + 5 + 6), bd(6));
    BigDecimal moveProb[][] = new BigDecimal[257][7];
    BigDecimal res[] = new BigDecimal[257];

    public void solving() {
        for (int i = 1; i <= 256; ++i) {
            for (int s = 1; s <= 6; ++s) {
                moveProb[i][s] = b16;
            }

            isprime[i] = primes.contains(i);
            if (grcnt != 0 && !isprime[i - 1] && !isprime[i] && !isprime[i + 1]) {
                System.out.println();
                processCurrentGroup();
                grcnt = 0;
            }
            System.out.print(i + " ");
            group[grcnt++] = i;
        }
        processCurrentGroup();
        System.out.println();

        BigInteger lcm = BigInteger.ONE;
        for (int n = 1; n <= 256; ++n) {
            BigDecimal r = res[n];
            if (r == null) {
                r = ZERO;

                int left = n == 1 ? 256 : n - 1;
                int right = n == 256 ? 1 : n + 1;
                BigDecimal prob = ZERO;
                for (int s = 1; s <= 6; ++s) {
                    prob = prob.add(moveProb[left][s].multiply(moveProb[right][s]));
                }
                for (int s = 1; s <= 6; ++s) {
                    r = r.add(estimateNormal(n, s, prob).multiply(b16));
                }
            }

            String fr = OtherUtils.formatDouble(r.doubleValue(), 10);
            int dotInd = fr.indexOf(".");
            char d = fr.charAt(dotInd + 2);
            System.out.println(n + ": " + fr + ": " + (d == '1'));
            if (d == '1') {
                lcm = MyMath.lcm(lcm, BigInteger.valueOf(n));
            }
        }

        System.out.println(lcm);
    }

    private void processCurrentGroup() {
        if (grcnt == 1) {
            assert !isprime[group[0]];
            return;
        }

        bSumVarProb = ZERO;
        Arrays.fill(scores, ZERO);
        Arrays.fill(edgeMoveProbs[0], ZERO);
        Arrays.fill(edgeMoveProbs[1], ZERO);

        process(0);

//        System.out.println(bSumVarProb);

        for (int i = 0; i < grcnt; ++i) {
            int n = group[i];
            if (isprime[n]) {
                res[n] = bd(n % 5 + 1);
            } else {
                res[n] = divide(scores[i], bSumVarProb);
            }
        }

        for (int s = 1; s <= 6; ++s) {
            moveProb[group[0]][s] = divide(edgeMoveProbs[0][s], bSumVarProb);
            moveProb[group[grcnt - 1]][s] = divide(edgeMoveProbs[1][s], bSumVarProb);
        }
    }

//    wrong: 92629129593000, 2223099110232000

    int moves[] = new int[15];
    BigDecimal scores[] = new BigDecimal[15];
    BigDecimal varScores[] = new BigDecimal[15];
    BigDecimal edgeMoveProbs[][] = new BigDecimal[2][7];
    BigDecimal bVarProb;
    BigDecimal bSumVarProb;

    private void process(int ind) {
        if (ind == grcnt) {
            if (countVariantScore()) {
                for (int i = 0; i < grcnt; ++i) {
                    scores[i] = scores[i].add(varScores[i].multiply(bVarProb));
                }
                bSumVarProb = bSumVarProb.add(bVarProb);
            }
            return;
        }

        for (int s = 1; s <= 6; ++s) {
            moves[ind] = s;
            process(ind + 1);
        }
    }

    private boolean countVariantScore() {
        bVarProb = bd(1);
        Arrays.fill(varScores, ZERO);

        for (int i = 0; i < grcnt; ++i) {
            //крайние непростые
            if (i == 0 || i == grcnt - 1) {
                varScores[i] = varScores[i].add(estimateEdge(group[i], moves[i]));
            } else {
                BigDecimal v = estimateInner(group[i], moves[i - 1], moves[i], moves[i + 1]);
                if (v == null) {
                    return false;
                }
                varScores[i] = varScores[i].add(v);
            }
        }
        edgeMoveProbs[0][moves[0]] = edgeMoveProbs[0][moves[0]].add(bVarProb);
        edgeMoveProbs[1][moves[grcnt - 1]] = edgeMoveProbs[1][moves[grcnt - 1]].add(bVarProb);
        return true;
    }

    private BigDecimal bd(double v) {
        return new BigDecimal(v, mc);
    }

    private BigDecimal divide(BigDecimal b1, BigDecimal b2) {
        return b1.divide(b2, mc);
    }

    private BigDecimal estimateInner(int n, int prev, int self, int next) {
        int n51 = n % 5 + 1; //{1..5}
        BigDecimal bn51 = bd(n51); //{1..5}
        if (isprime[n]) {
            if (prev == next) {
                // 0.5 => self
                // 0.5 => 
                //    n==2 => one of {1..6}
                //    n!=2 => one of {2..7}
                if (n51 == 1) {
                    if (self != 1) {
                        bVarProb = ZERO;
                        return null;
                    } else {
                        bVarProb = bVarProb.multiply(bd(0.5));
                    }
                } else {
                    //n51 = {2..5}
                    double prob = self == n51 ? 0.5 : 0;
                    prob += 0.5 * 1.0/6.0;
                    bVarProb = bVarProb.multiply(bd(prob));
                }

                return bn51;
            } else {
                if (n == 2 || self == n51) {
                    return bn51;
                }

                return null;
            }
        } else {
            if (prev == next) {
                return estimateNormal(n, self, BigDecimal.ONE);
            } else {
                return n % 2 == 1 ? bd(self) : bn51;
            }
        }
    }

    private BigDecimal estimateEdge(int n, int self) {
        return estimateNormal(n, self, b16);
    }

    private BigDecimal estimateNormal(int n, int s, BigDecimal probSameSides) {
        BigDecimal probDiffSides = BigDecimal.ONE.subtract(probSameSides);
        BigDecimal bs = bd(s);
        if (n % 2 == 1) {
//            return probDiffSides * s +
//                   probSameSides * (
//                           0.5 * s +
//                              0.5 * (
//                                   (2 + 3 + 4 + 5 + 6 + 7) * 1.0 / 6.0
//                              )
//                   );
            return probDiffSides.multiply(bs).add(
                    probSameSides.multiply(
                           b12.multiply(bs).add(
                                b12.multiply(sum27div6)
                           )
                   )
            );
        } else {
//            return (probDiffSides * (n % 5 + 1)) +
//                   probSameSides * (
//                           0.5 * s + 0.5 * (
//                                   (1 + 2 + 3 + 4 + 5 + 6) * 1.0 / 6.0
//                           )
//                   );
            return probDiffSides.multiply(bd(n%5 + 1)).add(
                    probSameSides.multiply(
                           b12.multiply(bs).add(
                                b12.multiply(sum16div6)
                           )
                   )
            );
        }
    }

}
