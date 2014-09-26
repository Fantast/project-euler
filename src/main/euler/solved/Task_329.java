package solved;

import tasks.ITask;
import tasks.Tester;
import utils.BigFraction;
import utils.log.Logger;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

//Answer : 199740353/29386561536000
public class Task_329 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_329());
        Logger.close();
    }

    Set<Integer> primes = new TreeSet<Integer>(
            Arrays.asList(
                    2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167,
                    173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359,
                    367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499));

    private BigFraction prob = BigFraction.ZERO;

    public void solving() {
        for (int i = 1; i <= 500; ++i) {
            prob = prob.add(find(i, 0));
        }

        System.out.println(prob.multiply(new BigFraction(1, 500)));
    }

    BigFraction f[][] = new BigFraction[501][16];

    private BigFraction find(int n, int cnt) {
        if (cnt == 15) {
            return BigFraction.ONE;
        }

        if (f[n][cnt] != null) {
            return f[n][cnt];
        }

        long denom = n == 500 || n == 1 ? 3 : 6;
        long numer = isPNeeded(cnt) != isPrime(n) ? 1 : 2;
        BigFraction prob = new BigFraction(numer, denom);

        BigFraction res = n == 500
                          ? BigFraction.ZERO
                          : prob.multiply(find(n + 1, cnt + 1));

        if (n > 1) {
            res = res.add(prob.multiply(find(n - 1, cnt + 1)));
        }

        return f[n][cnt] = res;
    }

    private boolean isPNeeded(int cnt) {
        return "PPPPNNPPPNPPNPN".charAt(cnt) == 'P';
    }

    private boolean isPrime(int n) {
        return primes.contains(n);
    }
}
