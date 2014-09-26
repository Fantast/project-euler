package solved;

import tasks.ITask;
import tasks.Tester;

import java.math.BigInteger;
import java.util.TreeSet;

import static java.math.BigInteger.ZERO;
import static utils.MyMath.getPrimeDivisors;

//Answer : 482316491800641154
//@see: http://www.research.att.com/~njas/sequences/A159907
public class Task_241 implements ITask {
    BigInteger LIM = new BigInteger("1000000000000000000");
    String ns[] = {"2", "24", "4320", "4680", "26208", "8910720", "17428320", "20427264",
        "91963648", "197064960", "8583644160", "10200236032",
        "21857648640", "57575890944", "57629644800", "206166804480",
        "17116004505600", "1416963251404800",
        "15338300494970880", "4320", "4680", "26208", "20427264", "197064960", "21857648640", "57575890944",
        "88898072401645056", "301183421949935616",
        "8910720", "17428320", "8583644160", "57629644800", "206166804480", "1416963251404800",
        "15338300494970880",
        "17116004505600", "75462255348480000"
        };

    public void solving() {
//        for (int n = 2; n < 10000000; n+=2) {
//            LongFraction f = new LongFraction(getDivisorsSum(n), n);
//            if (f.denom == 2) {
//                System.out.println(n + " : " + f + ": " + getPrimeDivisors(n));
//            }
//        }

        TreeSet<BigInteger> all = new TreeSet<BigInteger>();
        for (String n : ns) {
            all.add(new BigInteger(n));
        }

        BigInteger s = ZERO;
        for (BigInteger n : all) {
            if (n.compareTo(LIM) <= 0) {
                System.out.println(n + ": " + getPrimeDivisors(n.longValue()));
                s = s.add(n);
            }
        }

        System.out.println(s.longValue());
    }

    public static void main(String[] args) {
        Tester.test(new Task_241());
    }
}
