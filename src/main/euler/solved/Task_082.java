package solved;

import tasks.ITask;
import utils.pairs.IntPair;

import java.util.LinkedList;
import java.util.Queue;

import static java.lang.Math.min;
import static utils.OtherUtils.deepFillLong;

//Answer : 260324
public class Task_082 implements ITask {
    int d[][] = {
            {4445,2697,5115,718,2209,2212,654,4348,3079,6821,7668,3276,8874,4190,3785,2752,9473,7817,9137,496,7338,3434,7152,4355,4552,7917,7827,2460,2350,691,3514,5880,3145,7633,7199,3783,5066,7487,3285,1084,8985,760,872,8609,8051,1134,9536,5750,9716,9371,7619,5617,275,9721,2997,2698,1887,8825,6372,3014,2113,7122,7050,6775,5948,2758,1219,3539,348,7989,2735,9862,1263,8089,6401,9462,3168,2758,3748,5870}
            //stripped
    };

    long a[][];
    public void solving() {
        long res = 0;
        int n = d.length;
        a = new long[n][n];
        Queue<IntPair> q = new LinkedList<IntPair>();

        deepFillLong(a, Long.MAX_VALUE/2);
        for (int i = 0; i < n; ++i) {
            a[i][0] = d[i][0];
            q.offer(new IntPair(i, 0));
        }

        int di[] = {-1, 1, 0};
        int dj[] = {0, 0, 1};
        while ( !q.isEmpty() ) {
            IntPair p = q.poll();
            int i = p.a;
            int j = p.b;

            for (int k = 0; k < di.length; ++k) {
                int ni = i + di[k];
                int nj = j + dj[k];

                if (ni < 0 || ni>=n || nj<0 || nj>=n) continue;

                if (a[ni][nj] > a[i][j] + d[ni][nj]) {
                    a[ni][nj] = a[i][j] + d[ni][nj];
                    q.offer( new IntPair(ni, nj) );
                }
            }
        }

        res = Long.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            res = min(res, a[i][n-1]);
        }

        System.out.println( res );
    }
}
