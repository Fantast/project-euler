package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import java.math.BigInteger;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.valueOf;
import static utils.OtherUtils.replicate;

//Answer : 808981553
public class Task_463 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_463());
        Logger.close();
    }

    BigInteger lim = new BigInteger("3").pow(37);
//    BigInteger lim = new BigInteger("100");
    BigInteger TWO = valueOf(2);
    long mod = 1000000000L;
    BigInteger bmod = valueOf(mod);
    
    public void solving() {
        int pw = 0;
        BigInteger groupLen = ONE;
        while (groupLen.compareTo(lim) <= 0) {
            groupLen = groupLen.shiftLeft(1);
            pw++;
        }
        --pw;
        groupLen = groupLen.shiftRight(1);
        System.out.println(pw);
        System.out.println(groupLen);
        
        long s = new BigInteger(replicate("1", pw), 4).mod(bmod).longValue(); //sum of [1..(2^pw-1)]th
        System.out.println(s);
        
        BigInteger maxIndex = lim.subtract(groupLen.subtract(ONE)); //lim - (2^pw - 1)
        
        s = (s + groupSum(groupLen, maxIndex, TWO)) % mod;

        System.out.println();
        System.out.println(s);
    }

    private long groupSum(BigInteger groupLen, BigInteger maxIndex, BigInteger delta) {
        if (groupLen.compareTo(maxIndex) == 0) {
            return wholeSum(groupLen, delta);
        }
            
        BigInteger newGroupLen = groupLen.shiftRight(1);
        if (newGroupLen.compareTo(maxIndex) <= 0) {
            long s = wholeSum(newGroupLen, delta.shiftLeft(1));
            maxIndex = maxIndex.subtract(newGroupLen);
            s = (s + delta.multiply(maxIndex).mod(bmod).longValue()) % mod;
            s = (s + groupSum(newGroupLen, maxIndex, delta.shiftLeft(1))) % mod;
            return s;
        } else {
            return groupSum(newGroupLen, maxIndex, delta.shiftLeft(1));
        }
    }

    private long wholeSum(BigInteger groupLen, BigInteger delta) {
        //(a1 + a1+d*(n-1))*n/2 ==> a1 == 1
        return TWO.add(delta.multiply(groupLen.subtract(ONE))).multiply(groupLen).divide(TWO).mod(bmod).longValue();
    }
}
