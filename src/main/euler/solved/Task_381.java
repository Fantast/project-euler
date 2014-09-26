package solved;

import tasks.ITask;
import tasks.Tester;
import utils.MyMath;
import utils.log.Logger;

//Answer : 139602943319822
public class Task_381 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_381());
        Logger.close();
    }

    int LIM = 100000000;

    public void solving() {
        long res = 0;
        for (long p : MyMath.getPrimesBetween(5, LIM)) {
            long r = (p - MyMath.modInverse(8, p)) % p;
            res += (3*r) % p;
        }
        System.out.println(res);
    }
}
