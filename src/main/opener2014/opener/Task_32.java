package opener;

import tasks.ITask;
import tasks.Tester;
import utils.MyMath;
import utils.log.Logger;

import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;

//Answer :
public class Task_32 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_32());
        Logger.close();
    }

    long[] primes;
    int pc;

    public void solving() {
        primes = MyMath.getPrimesBetween(0, 10000000);
        pc = primes.length;
        System.out.println(pc);

        long max = primes[pc-1] * primes[pc-1];
        System.out.println(max);

        long p = 0;
        for (long i = 2; i <= max; ++i) {
            if (i % 100000 == 0) {
                System.out.println("Progress: " + i);
            }

            long it = i;
            long omega = 0;
            for (int pj = 0; pj < pc; ++pj) {
                long pr = primes[pj];
                while (it % pr == 0) {
                    it /= pr;
                    ++omega;
                }

                if (it < pr*pr) {
                    if (it > 1) {
                        ++omega;
                    }
                    break;
                }
            }

            p += (omega % 2 == 0 ? 1 : -1);
//            System.out.println(i + " : " + p);
            if (p == 0) {
                System.out.println(i);
                break;
            }
        }
    }
}
