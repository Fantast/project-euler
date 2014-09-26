package solved;

import tasks.ITask;
import tasks.Tester;
import utils.MyMath;
import utils.log.Logger;

import static utils.MyMath.gcd;

//Answer : 399788195976
//http://en.wikipedia.org/wiki/RSA_%28algorithm%29#cite_note-4
public class Task_182 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_182());
        Logger.close();
    }

    long p = 1009;
    long q = 3643;
    long n = p*q;
    long phi = (p-1)*(q-1);

    public void solving() {
        long conflicts[] = new long[]{0, 1, 346086, 1664850, 1664851, 2010936, 2010937, 3329701, 3675786 };

        long res = 0;
        long min = 9;
        E: for (long e = 2; e < phi; ++e) {
            if (e % 100000 == 0) {
                System.out.println("Progress: " + e);
            }
            if (gcd(e, phi) == 1 && gcd((e-1)/2, phi) == 1) {
                int cnt = 0;
                for (long m : conflicts) {
                    long c = MyMath.modPow(m, e, n);
                    if (m == c) {
                        ++cnt;
                        if (cnt > min) {
                            continue E;
                        }
                    }
                }

                if (cnt < min) {
                    res = e;
                    min = cnt;
                } else {
                    // == min
                    res += e;
                    System.out.println(e + " " + res);
                }
            }
        }
        System.out.println(res);
    }
}
