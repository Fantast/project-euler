package tasks;

import org.apfloat.Apint;
import org.apfloat.ApintMath;
import utils.MyMath;
import utils.log.Logger;

import java.util.Arrays;
import java.util.Random;

import static utils.MyMath.getPrimesBetween;

//Answer :
public class Task_266 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_266());
        Logger.close();
    }

    int pn;
    Apint primes[];
    Apint sq;
//    Apint max = Apint.ONE;
    Apint max = new Apint("2323218927976597371140445540972564030");

    Apint t16 = ApintMath.pow(new Apint(10), 16);

    public void solving() {
        MyMath.setMaxPrimesToCache(500);

//        long[] lpr = getPrimesBetween(1, 12);
        long[] lpr = getPrimesBetween(1, 190);
//        mixUp(lpr);

        pn = lpr.length;

        primes = new Apint[pn];

        Apint p = new Apint(1);
        for (int i = 0; i < pn; i++) {
            long pr = lpr[i];
            primes[i] = new Apint(pr);

            p = p.multiply(new Apint(pr));
        }

        sq = ApintMath.sqrt(p)[0];

        System.out.println(p);
        System.out.println(sq);

        find(0, Apint.ONE);

        System.out.println(max);
    }

    Random r = new Random();
    private void mixUp(long[] p) {
        System.out.println(Arrays.toString(p));
        for (int swaps = 0; swaps < 500; ++swaps) {
            int i = r.nextInt(p.length);
            int j = r.nextInt(p.length);

            long t = p[i];
            p[i] = p[j];
            p[j] = t;
        }

        System.out.println(Arrays.toString(p));
    }

    private void find(int ind, Apint current) {
        if (ind < primes.length) {
            Apint next = current.multiply(primes[ind]);
            if (next.compareTo(sq) <= 0) {
                find(ind + 1, current);
                find(ind + 1, next);
                return;
            }
        }

        if (current.compareTo(max) > 0) {
            max = current;
            System.out.println(max + ": " + max.mod(t16));
        }
    }
}
