package solved;

import tasks.ITask;
import tasks.Tester;
import utils.MyMath;

import java.util.ArrayList;

import static utils.MyMath.hasCommonDivisors;

//Answer : 676333270
public class Task_146 implements ITask {
    static long primes[] = MyMath.getCachedPrimesInternal();

    private long possRems[];
    private long foundation;
    private void initPossibleReminders(int procPrimes) {
        System.out.println("Finding possible reminders...");
        long gabs[] = {0, 2, 4, 2, 4, 14};

        foundation = 2;

        ArrayList<Long> possRems = new ArrayList<Long>(); possRems.add(1L);

        ArrayList<Long> fDivs = new ArrayList<Long>(); fDivs.add(2L);

        for (int i = 1; i < procPrimes; ++i) {
            long oldF = foundation;

            foundation *= primes[i];
            fDivs.add( primes[i] );

            ArrayList<Long> newRems = new ArrayList<Long>();

            for (long rem : possRems) {
                HERE:
                for (; rem < foundation; rem += oldF) {
                    long r = rem;
                    for (int j = 0; j < gabs.length; ++j) {
                        r += gabs[j];
                        if ( hasCommonDivisors(foundation, r) ) {
                            continue HERE;
                        }
                    }

                    newRems.add(rem);
                }
            }

            possRems.clear();
            possRems = newRems;
        }

        System.out.println("Foundation : " + foundation);
        System.out.println("Possible reminders [count="+possRems.size()+"] : " + possRems);

        this.possRems = new long[possRems.size()];
        for (int i = 0; i < possRems.size(); ++i) {
            this.possRems[i] = possRems.get(i);
        }
    }

    public void solving() {
        bruteForceAttack();
    }

    private void bruteForceAttack() {
        initPossibleReminders(7);

        int preCalc = 0;
        int res = preCalc;

        long wasTime = System.currentTimeMillis();
        long time;

        for (long j = 10; j <= 150000000; j+=2) {
            long i = j*j;

            boolean can = false;
            for (long rem : possRems) {
                if ( (i+1 - rem) % foundation == 0) {
                    can = true;
                    break;
                }
            }

            if ( can ) {
                if ( isPrime(i+1) && isPrime(i+3) && isPrime(i+7) && isPrime(i+9) && isPrime(i+13) && isPrime(i+27)
                        && !isPrime(i+19)
                        && !isPrime(i+21)
                        ) {
                    res += j;

                    System.out.println("   Founded number: " + j);
                    System.out.println("   Current overall sum : " + res );
                }
            }

            if (j%1000000 == 0) {
                time = System.currentTimeMillis() - wasTime;
                System.out.println(j + " - Calculation time : " + time + " ms.");
                System.out.println("            Current sum : " + res);
                wasTime = System.currentTimeMillis();
            }
        }

        System.out.println(res);
    }

    public static boolean isPrime(long n) {
        return MyMath.isPrime(n);
    }

    public static void main(String[] args) {
        Tester.test(new Task_146());
    }
}
