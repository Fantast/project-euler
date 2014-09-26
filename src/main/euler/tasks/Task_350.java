package tasks;

import utils.log.Logger;

import java.math.BigInteger;

import static utils.MyMath.bi;

//Answer :
public class Task_350 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_350());
        Logger.close();
    }

    long G = 1000000;
    long L = 1000000000000L;
    BigInteger BL = bi(L);
    public void solving() {
        BigInteger b = bi(2);
        while (b.compareTo(BL) < 0) {
//            System.out.println(b);
            b = b.nextProbablePrime();
        }
    }
}
