package solved;

import tasks.ITask;
import tasks.Tester;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import static java.math.BigInteger.ZERO;
import static utils.Combinatoric.C;
import static utils.Combinatoric.factorial;

//Answer : 0.001887854841
public class Task_239 implements ITask {

    public void solving() {
        BigInteger sum = ZERO;
        for (int j = 0; j <= 22; ++j) {
            BigInteger ds = C(22, j).multiply( factorial(75+j) );
            if (j%2==1) ds = ds.negate();
            sum = sum.add(ds);
        }

        sum = sum.multiply( C(25, 3) );
        System.out.println(new BigDecimal(sum).divide(new BigDecimal(factorial(100)), 12, RoundingMode.HALF_UP).doubleValue());
    }

    public static void main(String[] args) {
        Tester.test(new Task_239());
    }
}
