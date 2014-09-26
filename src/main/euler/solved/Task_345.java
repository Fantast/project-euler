package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import static java.lang.Math.max;
import static utils.MyMath.isBitSet;
import static utils.MyMath.setBit;
import static utils.OtherUtils.deepFillInt;

//Answer : 13938
public class Task_345 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_345());
        Logger.close();
    }

    String s = "7  53 183 439 863 497 383 563  79 973 287  63 343 169 583\n" +
               "627 343 773 959 943 767 473 103 699 303 957 703 583 639 913\n" +
               "447 283 463  29  23 487 463 993 119 883 327 493 423 159 743\n" +
               "217 623   3 399 853 407 103 983  89 463 290 516 212 462 350\n" +
               "960 376 682 962 300 780 486 502 912 800 250 346 172 812 350\n" +
               "870 456 192 162 593 473 915  45 989 873 823 965 425 329 803\n" +
               "973 965 905 919 133 673 665 235 509 613 673 815 165 992 326\n" +
               "322 148 972 962 286 255 941 541 265 323 925 281 601  95 973\n" +
               "445 721  11 525 473  65 511 164 138 672  18 428 154 448 848\n" +
               "414 456 310 312 798 104 566 520 302 248 694 976 430 392 198\n" +
               "184 829 373 181 631 101 969 613 840 740 778 458 284 760 390\n" +
               "821 461 843 513  17 901 711 993 293 157 274  94 192 156 574\n" +
               " 34 124   4 878 450 476 712 914 838 669 875 299 823 329 699\n" +
               "815 559 813 459 522 788 168 586 966 232 308 833 251 631 107\n" +
               "813 883 451 509 615  77 281 613 459 205 380 274 302  35 805";

    int num[][];
    int n;

    public void solving() {
        String spl[] = s.split("\\s+");
        n = (int) Math.sqrt(spl.length);
        num = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                num[i][j] = Integer.parseInt(spl[i * n + j]);
            }
        }

        deepFillInt(bs, -1);

        System.out.println(getBestSum(0, 0));
    }

    int bs[][] = new int[20][1 << 17];

    private int getBestSum(int col, int rowUsedMask) {
        if (col == n) {
            return 0;
        }

        if (bs[col][rowUsedMask] != -1) {
            return bs[col][rowUsedMask];
        }

        int best = 0;
        for (int i = 0; i < n; ++i) {
            if (!isBitSet(rowUsedMask, i)) {
                best = max(best, num[i][col] + getBestSum(col + 1,
                                                          setBit(rowUsedMask, i)));
            }
        }

        return bs[col][rowUsedMask] = best;
    }
}
