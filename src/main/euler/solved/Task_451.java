package solved;

import tasks.ITask;
import tasks.Tester;
import utils.MyMath;
import utils.log.Logger;

//Answer : 153651073760956
public class Task_451 implements ITask {

    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_451());
        Logger.close();
    }

    int LIM = 20000000;

    long n;
    long factors[] = new long[20];
    int factorCnt;
    
    public void solving() {
        long[] primes = MyMath.getCachedPrimes();
        long res = 0;
        for (n = 3; n <= LIM; ++n) {
            if (n%100000 == 3) {
                System.out.println("Progress: " + n);
            }

            factorCnt = 0;

            long n2 = n;
            for (long p : primes) {
                if (n2 % p == 0) {
                    factors[factorCnt] = 1;
                    while (n2 % p == 0) {
                        factors[factorCnt] *= p;
                        n2 /= p;
                    }
                    factorCnt++;
                }

                if (p * p >= n2) {
                    if (n2 != 1) {
                        factors[factorCnt++] = n2;
                    }
                    break;
                }
            }
            
            findMaxSolution();
            res += maxSolution;
            if (n == 7 || n == 15 || n==100 || n == 225 || n == 35 ) {
                System.out.println(n + ": " + maxSolution);
            }
        }
        System.out.println(res);
    }
    
    long reminders[] = new long[20];
    long maxSolution;
    private void findMaxSolution() {
        maxSolution = 0;
        long factor = factors[0];
        if (factor % 2 == 0) {
            reminders[0] = 1;
            findMaxSolution(1);
            
            if (factor > 2) {
                reminders[0] = factor - 1;
                findMaxSolution(1);
            }
            
            if (factor > 4) {
                reminders[0] = factor/2 - 1;
                findMaxSolution(1);
                reminders[0] = factor/2 + 1;
                findMaxSolution(1);
            }
        } else {
            findMaxSolution(0);
        }
    }

    private void findMaxSolution(int index) {
        if (index == factorCnt) {
            long solution = MyMath.solveChineseRemainderTheorem(reminders, factors, factorCnt) % n;
            if (solution != n - 1) {
                maxSolution = Math.max(maxSolution, solution);
            }
            return;
        }

        long factor = factors[index];

        reminders[index] = 1;
        findMaxSolution(index + 1);
        reminders[index] = factor - 1;
        findMaxSolution(index + 1);
    }
}
