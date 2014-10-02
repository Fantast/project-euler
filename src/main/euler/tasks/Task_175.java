package tasks;

import java.util.*;

import static utils.MyMath.*;

//Answer :
public class Task_175 extends AbstractTask {
    public int a = 123456789;
    public int b = 987654321;
//    public int a = 13;
//    public int b = 17;
    int gcd = gcd(a, b);
    int num = a / gcd;
    int den = b / gcd;
    int BEG = num - 1;

    int p2[] = new int[32];
    int mask[] = new int[32];

    public void solving() {
        Arrays.fill(d, -1);
        Arrays.fill(d0, -1);

        for (int i = 0; i < p2.length; ++i) {
            p2[i] = 1 << i;
            mask[i] = p2[i] - 1;
        }

        System.out.println(gcd);
        System.out.println(num + " " + den);

//        for (int i = 0; i < 100; ++i) {
//            System.out.println(Integer.toBinaryString(i) + ": " + count(i));
//        }

//        test(1);
//        test(2);
//        test(3);
//        test(4);
//        test(5);
//        test(6);
//        test(10);
        test(240);
//        test(471);

//        BEG = 1;
//        Arrays.fill(d, -1);
//        long fn1 = count(BEG - 1);
//        for (int n = BEG; ; ++n) {
//            progress1000000(n);
//            long fn = count(n);
//
//            long g = gcd(fn, fn1);
//            if (fn / g == num && fn1 / g == den) {
//                System.out.println(n);
//                break;
//            }
////            System.out.println(fn + "/" + fn1);
//
//            fn1 = fn;
//        }
    }

    private void test(int n) {
        System.out.println("--------------------------");
        System.out.println(n + ": count0: " + count0(n));
        System.out.println("--------------------------");
        System.out.println(n + ": count: " + count(n));
//        simpleCount(n);
    }

    private void simpleCount(int n) {
        Set<List<Integer>> all = new HashSet<>();
        for (int a = 0; a < n; ++a) {
            int b = n - a;

            List<Integer> ads = new ArrayList<>();
            for (int j = 0; j < 2; ++j) {
                int p2 = 1;
                while (b != 0) {
                    if ((b & 1) != 0) {
                        ads.add(p2);
                    }
                    p2 <<= 1;
                    b >>= 1;
                }
                b = a;
            }
            Collections.sort(ads, Comparator.<Integer>reverseOrder());
            all.add(ads);
        }
        System.out.println(n + ": simple: " + all.size());
//        for (List<Integer> e : all) {
//            System.out.println(e);
//        }
    }

    long d[] = new long[300000];
    long d0[] = new long[300000];

    String indent = "";
    private long count(int n) {
        if (n < 2) {
            return 1;
        }

        if (d[n] != -1) {
            return d[n];
        }

        long res = 0;
        int msb = mostSignificantBit(n);

        int i = 0;
        int m = mask[i];
        while (i < mask.length && n > m) {
            res += count(n & m);
            if (isBitSet(n & m, i)) {
                res -= count(unSetBit(n & m, i));
//                if (n > mask[i+1]) {
//                    res -= count(unSetBit(n & m, i));
//                }
            }
            m = mask[++i];
        }

        int nn = unSetBit(n, msb);
        res += count(nn);
        res += count(setBit(nn, msb - 1));
        if (isBitSet(nn, msb - 1)) {
            res -= count(unSetBit(nn, msb - 1));
        }

//        return d[n] = res;
        return res;
    }

    private long count0(int n) {
        if (n < 2) {
            return 1;
        }
        if (n == 0) {
            return 1;
        }

        if (d0[n] != -1) {
            return d0[n];
        }

        long res = 0;

        int msb = mostSignificantBit(n);
        int nn = unSetBit(n, msb);

        String oIndent = indent;

        indent += "   ";
        long с1 = count0(nn);
        res += с1;
        indent = oIndent;
        System.out.println(indent + "+ " + с1);

        indent += "   ";
        long с2 = count0(setBit(nn, msb - 1));
        res += с2;
        indent = oIndent;
        System.out.println(indent + "+ " + с2);

        if (isBitSet(nn, msb - 1)) {
            indent += "   ";
            long с3 = count0(unSetBit(nn, msb - 1));
            res -= с3;
            indent = oIndent;
            System.out.println(indent + "- " + с3);
        }

//        return d0[n] = res;
        return res;
    }

    public static void main(String[] args) {
        Tester.test(new Task_175());
    }
}
