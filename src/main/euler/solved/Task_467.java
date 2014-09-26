package solved;

import tasks.AbstractTask;
import tasks.Tester;
import utils.MyMath;
import utils.OtherUtils;
import utils.log.Logger;

import java.math.BigInteger;

import static java.lang.Math.max;
import static java.lang.Math.min;

//Answer : 775181359
public class Task_467 extends AbstractTask {

    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_467());
        Logger.close();
    }


    BigInteger mod = new BigInteger("1000000007");

    int n = 10000;
//    int n = 100;
//    int n = 10;
    int p[] = new int[n];
    int c[] = new int[n];
    int pc = 0;
    int cc = 0;
    
    int best[][] = new int[n+1][n+1];
    int res[] = new int[2*n];
    int resc;
    
    
    public void solving() {
        MyMath.setMaxPrimesToCache(n+2);
        for (int i = 2; pc < n || cc < n; ++i) {
            if (MyMath.isPrime(i)) {
                p[pc++] = digitalRoot(i);
            } else if (cc < n) {
                c[cc++] = digitalRoot(i);
            }
        }
        
//        n = 3;
//        p = new int[] {2, 1, 3};
//        c = new int[] {2, 5, 1};

        OtherUtils.deepFillInt(best, -1);
        int mx = 0;
        for (int i = 0; i < n; ++i) {
            int common = maxCommon(0, i);
            mx = max(mx, common);
        }

        resc = 2*n - mx;

        for (int i = 0; i < n; ++i) {
            if (best[0][i] == mx) {
                construct(0, 0, i, mx);
            }
        }

        String sn = "";
        for (int i = 0; i < resc; ++i) {
            sn += res[i];
        }

        System.out.println(sn);
        System.out.println(new BigInteger(sn).mod(mod));
    }
    
    private void construct(int ind, int src, int check, int mx) {
        if (src == n && check == n) {
            return;
        }
        
        if (src == n) {
            res[ind] = c[check];
            construct(ind + 1, src, check + 1, mx);
        } else if (check == n) {
            res[ind] = p[src];
            construct(ind + 1, src + 1, check, mx);
        } else {
            int d = 20;
            if (p[src] == c[check] && best[src + 1][check + 1] == mx-1) d = p[src];
            if (best[src+1][check] == mx) d = min(d, p[src]);
            if (best[src][check+1] == mx) d = min(d, c[check]);
            if (d > 9) throw new IllegalStateException("Strange...");
            
            if (p[src] == d && p[src] == c[check] && best[src + 1][check + 1] == mx-1) {
                res[ind] = d;
                construct(ind + 1, src + 1, check + 1, mx - 1);
                return;
            }
            
            if (p[src] == d && best[src+1][check] == mx) {
                res[ind] = d;
                construct(ind + 1, src + 1, check, mx);
                return;
            }

            if (c[check] == d && best[src][check+1] == mx) {
                res[ind] = d;
                construct(ind + 1, src, check + 1, mx);
            }
        }
    }

    private int maxCommon(int src, int check) {
        int res = best[src][check];
        if (res == -1) {
            if (src == n || check == n) {
                return best[src][check] = 0;
            }
            res = max(maxCommon(src + 1, check), maxCommon(src, check + 1));
            
            if (p[src] == c[check]) {
                res = max(res, 1 + maxCommon(src + 1, check + 1));
            }
            
            return best[src][check] = res;
        }
        
        return res;
    }

    private int digitalRoot(int n) {
        while (n > 9) {
            n = MyMath.sumOfDigits(n);
        }
        return n;
    }
}
