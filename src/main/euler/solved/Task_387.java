package solved;

import tasks.ITask;
import tasks.Tester;
import utils.MyMath;
import utils.log.Logger;

import java.util.PriorityQueue;
import java.util.Queue;

//Answer : 696067597313468
public class Task_387 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_387());
        Logger.close();
    }

    static class Harsh implements Comparable<Harsh> {
        long n;
        int sum;

        Harsh(long n, int sum) {
            this.n = n;
            this.sum = sum;
        }

        @Override
        public int compareTo(Harsh o) {
            return n < o.n
                   ? -1
                   : (n > o.n ? 1 : 0);
        }
    }

    long LIM = 100000000000000L;
    long LIM10 = LIM / 10;

    public void solving() {
        Queue<Harsh> q = new PriorityQueue<Harsh>();
        for (int i = 1; i < 10; ++i) {
            q.add(new Harsh(i, i));
        }

        long res = 0;
        while (!q.isEmpty()) {
            Harsh h = q.poll();
            for (int d = 0; d < 10; ++d) {
                long n = h.n * 10 + d;
                int s = h.sum + d;

                if (n <= LIM10 && n % s == 0) {
                    q.add(new Harsh(n, s));
                }
            }

            if (isPrime(h.n / h.sum)) {
                for (int d = 1; d < 10; d += 2) {
                    long probp = h.n * 10 + d;
                    if (isPrime(probp)) {
                        res += probp;
                    }
                }
            }
        }

        System.out.println(res);
    }

    private boolean isPrime(long n) {
//        return MyMath.isPrime(n);
        return MyMath.isProbablePrime(n, 5);
    }
}
