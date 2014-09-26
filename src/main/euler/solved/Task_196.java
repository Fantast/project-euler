package solved;

import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;

import static utils.MyMath.isProbablePrime;

//Answer : 322303240771079935
public class Task_196 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_196());
        Logger.close();
    }

    public void solving() {
        System.out.println(count(2));
        System.out.println(count(3));
        System.out.println(count(8));
        System.out.println(count(9));
        System.out.println(count(10000));
        System.out.println(count(5678027) + count(7208785));
    }


    Boolean isp[];
    long base = 0;
    private long count(int r) {
        isp = new Boolean[5*(r+3)];
        long a1 = (1L + r-1)*(r-1L)/2L;
        long a2 = a1 + r - 1;
        base = a1 - (r - 2 + r - 1);
        long res = 0;
        for (long a = a1; a <= a2; ++a) {
            if (!isPrime(a)) continue;

            if (check(a, r)) { res += a; continue; }

            //left
            if (a != a1 && check(a - 1, r)) { res += a; continue; }

            //right
            if (a != a2 && check(a + 1, r)) { res += a; continue; }

            //up
            if (a != a2 && check(a - r + 1, r - 1)) { res += a; continue; };

            //up-left
            if (a != a1 && check(a - r, r - 1))  { res += a; continue; }

            //up-right
            if (a < a2-1 && check(a - r + 2, r - 1))  { res += a; continue; }

            //down
            if (check(a + r, r + 1))  { res += a; continue; }

            //down-left
            if (a != a1 && check(a + r - 1, r + 1))  { res += a; continue; }

            //down-right
            if (check(a + r + 1, r + 1))  { res += a; continue; }
        }
        return res;
    }

    private boolean check(long a, int r) {
        long a1 = (1L + r-1)*(r-1L)/2L;
        long a2 = a1 + r - 1;

        if (!isPrime(a)) return false;

        int c = 1;

        //left
        if (a != a1 && isPrime(a - 1)) {
            c++;
        }
        if (a != a2 && isPrime(a + 1)) c++;
        if (c == 3) {return true; }

        //up
        if (a != a2 && isPrime(a - r + 1)) c++;
        if (c == 3) {return true; }

        //up-left
        if (a != a1 && isPrime(a - r)) c++;
        if (c == 3) {return true; }

        //up-right
        if (a < a2-1 && isPrime(a - r + 2)) c++;
        if (c == 3) {return true; }

        //down
        if (isPrime(a + r)) c++;
        if (c == 3) {return true; }

        //down-left
        if (a != a1 && isPrime(a + r - 1)) c++;
        if (c == 3) {return true; }

        //down-right
        if (isPrime(a + r + 1)) c++;

        return c == 3;
    }

    private boolean isPrime(long n) {
        int ind = (int) (n - base);
        Boolean res = isp[ind];
        if (res == null) {
            res = isp[ind] = isProbablePrime(n, 2);
        }
        return res;
    }
}
