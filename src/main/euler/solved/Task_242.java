package solved;

import tasks.ITask;
import tasks.Tester;

import java.math.BigInteger;

import static java.math.BigInteger.*;

//Answer : 997104142249036713
public class Task_242 implements ITask {
    BigInteger p3[] = new BigInteger[100];

    private BigInteger b2 = valueOf(2);
    
    long n = 1000000000000L;
    public void solving() {
        p3[0] = ONE;
        for (int i = 1; i < 50; ++i) {
            p3[i] = p3[i-1].multiply(valueOf(3));
        }

        System.out.println(count(45, (n-1)/4+1  ));
    }
    
    private BigInteger count(int p2c, long n) {
        if (p2c == 0) return ONE;
        if (n <= 0) return ZERO;

        long cn = 1L << (p2c-1L);
        if (n > cn) {
            return p3[p2c-1].add( b2.multiply(count(p2c - 1, n - cn)) ) ;
        } else {
            return count(p2c - 1, n);
        }
    }

    public static void main(String[] args) {
        Tester.test(new Task_242());
    }
}
