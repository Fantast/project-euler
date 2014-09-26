package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import java.math.BigInteger;
import java.util.TreeSet;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;
import static utils.MyMath.bi;

//Answer : 336108797689259276
public class Task_346 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_346());
        Logger.close();
    }

    BigInteger bn = bi(1000000000000L);

    TreeSet<BigInteger> r = new TreeSet<BigInteger>();

    public void solving() {
        r.add(ONE);
        for (int b = 2; ; ++b) {
            BigInteger B = bi(b);
            BigInteger k = bi(1 + b).add(B.pow(2));
            if (k.compareTo(bn) > 0) {
                break;
            }
            BigInteger bp = B.pow(3);
            while (k.compareTo(bn) < 0) {
                r.add(k);
                k = k.add(bp);
                bp = bp.multiply(B);
            }
        }

        BigInteger res = ZERO;
        for (BigInteger n : r) {
            res = res.add(n);
        }
        System.out.println(res);
    }
}
