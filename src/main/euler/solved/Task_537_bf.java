package solved;

import tasks.AbstractTask;
import tasks.Tester;
import utils.MyMath;
import utils.log.Logger;

import java.util.concurrent.CountDownLatch;

//Answer : 779429131
public class Task_537_bf extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_537_bf());
        Logger.close();
    }

    int n = 20000;

    long MOD = 1004535809;

    long w[] = new long[n + 1];

    long t1[] = new long[n + 1];
    long t2[] = new long[n + 1];
    long tmp[];

    CountDownLatch initLatch;
    CountDownLatch middleLatch;
    CountDownLatch beginLatch;
    CountDownLatch endLatch;

    public void solving() throws InterruptedException {
        long primes[] = MyMath.primes(n + 1);
        w[0] = 1;
        for (int i = 1; i <= n; ++i) {
            w[i] = primes[i] - primes[i-1];
        }

        beginLatch = new CountDownLatch(1);
        endLatch = new CountDownLatch(4);
        initLatch = new CountDownLatch(4);

        Compute cc[] = new Compute[4];
        for (int i = 0; i < 4; ++i) {
            cc[i] = new Compute(n/4*i + 1, n/4*(i+1));
            cc[i].start();
        }

        //noinspection ManualArrayCopy
        for (int i = 0; i <= n; ++i) {
            t1[i] = w[i];
        }

        for (int ki = 2; ki <= n; ++ki) {
            initLatch.await();

            progress(ki);
            t2[0] = 1;

            middleLatch = new CountDownLatch(1);
            beginLatch.countDown();

            endLatch.await();
            tmp = t1;
            t1 = t2;
            t2 = tmp;

            beginLatch = new CountDownLatch(1);
            endLatch = new CountDownLatch(4);
            initLatch = new CountDownLatch(4);

            middleLatch.countDown();
        }

        System.out.println(t1[n]);
    }

    class Compute extends Thread {
        private final int begin;
        private final int end;

        public Compute(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }

        @Override
        public void run() {
            try {
                for (int ki = 2; ki <= n; ++ki) {
                    initLatch.countDown();
                    beginLatch.await();
                    for (int ni = begin; ni <= end; ++ni) {
                        long r = 0;
                        for (int i = 0; i <= ni; ++i) {
                            r = (r + w[i] * t1[ni - i]) % MOD;
                        }
                        t2[ni] = r;
                    }
                    endLatch.countDown();
                    middleLatch.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
