package tasks;

import utils.log.Logger;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.lang.Math.abs;

//Answer :
public class Task_461 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_461());
        Logger.close();
    }

    ExecutorService executor = Executors.newFixedThreadPool(4);

    double pi = Math.PI;
    double denom = 10000;

    volatile double beste = 100;
    double best = 0;
    long ba;
    long bb;
    long bc;
    long bd; 
    public void solving() {
        Future<?> f1 = executor.submit(new Computation(0, 1000));
        Future<?> f2 = executor.submit(new Computation(1001, 2300));
        Future<?> f3 = executor.submit(new Computation(2301, 4000));
        Future<?> f4 = executor.submit(new Computation(4001, 15000));

        try {
            f1.get();
            f2.get();
            f3.get();
            f4.get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("OOPS");
        }


        System.out.println(best);
        System.out.println(beste);
        System.out.println(ba);
        System.out.println(bb);
        System.out.println(bc);
        System.out.println(bd);
    }

    private double f(double a) {
        return Math.exp(a/denom) - 1;
    }
    
    public synchronized void setBest(double newe, double newbest, int a, int b, int c, int d) {
        if (newe < beste) {
            beste = newe;
            best = newbest;
            ba = a;
            bb = b;
            bc = c;
            bd = d;
            System.out.println("New best:");
            System.out.println(best);
            System.out.println(beste);
            System.out.println(ba);
            System.out.println(bb);
            System.out.println(bc);
            System.out.println(bd);
            System.out.println(ba * ba + bb * bb + bc * bc + bd * bd);
        }
    }
    
    private class Computation implements Runnable {
        public final int astart;
        public final int aend;
        
        private Computation(int astart, int aend) {
            this.astart = astart;
            this.aend = aend;
        }

        @Override
        public void run() {
            for (int a = astart; a <= aend; ++a) {
                System.out.println("Progress A: " + a);
                double sa = f(a);
                if (sa > pi && sa - pi > beste) break;
                for (int b = a; ; ++b) {
                    if (b % 100 == 0) {
                        System.out.println("Progress B: " + b);
                    }
                    double sb = sa + f(b);
                    if (sb > pi && sb - pi > beste) break;
                    for (int c = b; ; ++c) {
                        double sc = sb + f(c);
                        if (sc > pi && sc - pi > beste) break;
                        for (int d = c; ; ++d) {
                            double sd = sc + f(d);
                            double newe = abs(sd - pi);
                            if (newe < beste) {
                                setBest(newe, sd, a, b, c, d);
                            } else if (sd > pi) {
                                break;
                            }
                        }
                    }
                }
            }

            System.out.println("Finished: " + astart + " - " + aend);
        }
    }  
}
