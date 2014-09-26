package solved;

import tasks.ITask;
import tasks.Tester;

import java.util.Arrays;

import static utils.MyMath.getPrimesBetween;
import static utils.MyMath.isPrime;

//Answer : 26033
public class Task_060 implements ITask {

    public void solving2() {
        long pr[] = getPrimesBetween(3, 10000);
        System.out.println(Arrays.toString(pr));
    }

    public void solving() {
        long pr[] = getPrimesBetween(3, 10000);
        int n = pr.length;
        
        for (int a = 4; a < n; ++a) {
            long p1 = pr[a];
            for (int b = a+1; b < n; ++b) {
                long p2 = pr[b];
                if (!isPrime(merge(p1, p2)) || !isPrime(merge(p2, p1))) continue;
                for (int c = b+1; c < n; ++c) {
                    long p3 = pr[c];
                    if (!isPrime(merge(p1, p3)) || !isPrime(merge(p3, p1))) continue;
                    if (!isPrime(merge(p2, p3)) || !isPrime(merge(p3, p2))) continue;
                    for (int d = c+1; d < n; ++d) {
                        long p4 = pr[d];
                        if (!isPrime(merge(p1, p4)) || !isPrime(merge(p4, p1))) continue;
                        if (!isPrime(merge(p2, p4)) || !isPrime(merge(p4, p2))) continue;
                        if (!isPrime(merge(p3, p4)) || !isPrime(merge(p4, p3))) continue;
                        System.out.print("Found 4: ");
                        print(p1, p2, p3, p4);
                        for (int e = d+1; e < n; ++e) {
                            long p5 = pr[e];

                            if (!isPrime(merge(p1, p5)) || !isPrime(merge(p5, p1))) continue;
                            if (!isPrime(merge(p2, p5)) || !isPrime(merge(p5, p2))) continue;
                            if (!isPrime(merge(p3, p5)) || !isPrime(merge(p5, p3))) continue;
                            if (!isPrime(merge(p4, p5)) || !isPrime(merge(p5, p4))) continue;

                            print(p1, p2, p3, p4, p5);
                            System.out.println(p1 + p2 + p3 + p4 + p5);
                            return;
                        }
                    }
                }
            }
        }
    }

    private long merge(long a, long b) {
        return Long.parseLong(a + "" + b);
    }

    public static void main(String[] args) {
        Tester.test(new Task_060());
    }

    private void print(long... p) {
        System.out.println(Arrays.toString(p));
    }
}
