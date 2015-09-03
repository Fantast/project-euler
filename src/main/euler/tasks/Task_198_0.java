package tasks;

import utils.LongFraction;
import utils.log.Logger;

import java.util.Iterator;
import java.util.TreeSet;

//Answer :
public class Task_198_0 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_198_0());
        Logger.close();
    }

    public long LIM = 1000;
//    public long LIM = 6;

//    For 10^3, 2285
//    For 10^5, < 1/100, I got 50271 = (321 + 49950) correct
//    For 10^6, < 1/100, I got 509718 = (9768 + 499950) miss some

    public LongFraction f100 = new LongFraction(1, 1);


    public TreeSet<LongFraction> res = new TreeSet<>();

    public void solving() {
        TreeSet<LongFraction> all = new TreeSet<>();
        TreeSet<LongFraction> all2 = new TreeSet<>();

        all.add(LongFraction.ZERO);
        all.add(LongFraction.ONE);

        for (long i = 2; i <= LIM; ++i) {
            if (progress100(i)) {
                System.out.println(all.size());
                System.out.println(res.size());
            }

            Iterator<LongFraction> iterator = all.iterator();
            LongFraction f1 = iterator.next();
            all2.add(f1);
            while (iterator.hasNext()) {
                LongFraction f2 = iterator.next();
                reg(f1, f2);

                LongFraction nf = new LongFraction(f1.numer + f2.numer, f1.denom + f2.denom);
                if (nf.denom == i) {
                    all2.add(nf);
                    System.out.println("asdfasdfasfd");
                    System.out.println("\0");
                }

                f1 = f2;
                all2.add(f1);
            }

//            all = new TreeSet(all2.headSet(all2.higher(f100), true));
            TreeSet<LongFraction> t = all;
            all = all2;
            all2 = t;
            all2.clear();

//            System.out.println(all);
        }
        System.out.println(res.size());
        System.out.println(res);
    }

    public void reg(LongFraction f1, LongFraction f2) {
        LongFraction nf = f1.add(f2).divide(2);
//        if (nf.denom <= LIM && nf.compareTo(f100) <= 0) {
        if (nf.denom <= LIM) {
//            System.out.println(nf);
            res.add(nf);
        }
    }
}
