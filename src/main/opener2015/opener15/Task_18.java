package opener15;

import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;

import java.math.BigInteger;

//Answer : 174224571863520493293247799005065324265472
public class Task_18 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_18());
        Logger.close();
    }

    BigInteger b1 = new BigInteger("16820908843987997"); // = 7 × 17 × 14767 × 9572165989
    BigInteger b2 = new BigInteger("3794765361567513");  // = 3 × 1264921787189171
    BigInteger b3 = new BigInteger("1");
    public void solving() {
        while (!b2.isProbablePrime(5)) {
            b2 = b1.add(b2);
            b1 = b2.subtract(b1); //b2
            b3 = b3.add(b3);
        }
        System.out.println(b3);
    }
}
