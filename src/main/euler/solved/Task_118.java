package solved;

import tasks.ITask;
import tasks.Tester;
import utils.OtherUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static utils.MyMath.isBitSet;
import static utils.MyMath.setBit;

//Answer : 44680
public class Task_118 implements ITask {
    final int mCnt = 1<<9;
    private int all = mCnt - 1;

    int n = 0;
    long primes[] = new long[50000];
    int prMasks[] = new int[50000];

    public void solving() {
        System.out.println("Reading primes...");
        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader("./files/primes6000000.txt")));
            while (sc.hasNextLong()) {
                long pr = sc.nextLong();
                if (pr > 100000000) {
                    break;
                }

                addPrime(pr);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return;
        }

        System.out.println("Primes are read, solving...");

        OtherUtils.deepFillLong(d, -1);

        System.out.println(count(0, 0, 0));
    }

    long d[][] = new long[50000][mCnt];
    long path[] = new long[100];
    private long count(int ind, int beg, int msk) {
        if (d[beg][msk] != -1) return d[beg][msk];

        if (msk == all) {
            for (int i = 0; i < ind; ++i) {
                System.out.print(path[i] + " ");
            }
            System.out.println();
            return d[beg][msk] = 1;
        }

        long r = 0;
        for (int i = beg; i < n; ++i) {
            if ((msk & prMasks[i]) == 0) {
                path[ind] = primes[i];
                r += count(ind+1, i+1, msk | prMasks[i]);
            }
        }

        return d[beg][msk] = r;
    }

    private void addPrime(long pr) {
        String ps = String.valueOf(pr);

        int msk = 0;
        for (int i = 0; i < ps.length(); ++i) {
            int d = ps.charAt(i) - '0';
            //zero's not allowed

            if (d == 0) {
                return;
            }
            if (isBitSet(msk, d - 1)) {
                //not a pandigital
                return;
            }

            msk = setBit(msk, d - 1);
        }

        prMasks[n] = msk;
        primes[n++] = pr;
    }

    public static void main(String[] args) {
        Tester.test(new Task_118());
    }
}