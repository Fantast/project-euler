package solved

import tasks.AbstractTask
import tasks.Tester
import utils.MyMath
import utils.log.Logger

//Answer : 3557005261906288
class Task_536 : AbstractTask() {
    val LIM = 1000000L;
//    val LIM = 1000000000000L;

    var sum = 11L;
    var maxprime = 0L;
    var primes: LongArray = LongArray(0);
    var factors: LongArray = LongArray(0);
    var isPrime: BooleanArray = BooleanArray(0);

    override fun solving() {
        MyMath.setMaxPrimesToCache(80000);
        primes = MyMath.getPrimesBetween(0, Math.sqrt((LIM + 4).toDouble()).toLong() + 3L)
        maxprime = primes.last()

        factors = LongArray(primes.size);
        isPrime = BooleanArray(maxprime.toInt() + 1)

        for (p in primes) {
            isPrime[p.toInt()] = true
        }

        println("go")

        find(0, 1, 1L)

        println(sum);
    }

    fun find(factInd: Int, ind: Int, n: Long) {
        if (ind == primes.size) {
            return;
        }

        if (factInd > 0) {

            // solving n*x ≡ -3 (mod q-1), q is largest factor, than x is the next factor
            // 1st case 3!|n and 3!|q-1 => n * x - k * (q-1) = -3
            // 2nd case 3|n and 3|q-1 => (n/3)*x - k*(q-1)/3 = -1

            val x: Long;
            val g: Long;

            val lastFactor = factors[factInd - 1]
            var q = lastFactor - 1;
            if (n % 3 == 0L && q % 3 == 0L) {
                // x = -(n/3)^(-1) (mod (q-1)/3), n%3 == 0 && q-1 % 3 == 0
                q /= 3;
                val rr = MyMath.modInverseWithRem(n / 3 % q, q);
                x = q - rr[1]
                g = rr[0]
            } else {
                // x = -3*n^(-1) (mod q-1), n%3 != 0 || q-1 % 3 != 0
                val rr = MyMath.modInverseWithRem(n % q, q);
                x = (q - 3) * rr[1] % q;
                g = rr[0]
            }

            if (g == 1L) {
                // so possible future numbers are nn = x + k * q
                // but also nn = n * prime, and prime > lastFactor

                // find min candidate
                var minprime = primes[ind];
                var candidate = minprime / q * q + x;
                if (candidate < minprime) {
                    candidate += q;
                }

                var nn = n * candidate;
                TOP@ while (nn <= LIM && candidate <= maxprime) {
                    if (isPrime[candidate.toInt()]) {
                        if ((nn + 3) % (candidate - 1) != 0L) {
                            candidate += q;
                            nn = n * candidate
                            continue;
                        }

                        for (i in 0..factInd - 1) {
                            if ((nn + 3) % (factors[i] - 1) != 0L) {
                                candidate += q;
                                nn = n * candidate
                                continue@TOP;
                            }
                        }

                        sum += nn;
                    }
                    candidate += q;
                    nn = n * candidate
                }
            }
        }

        for (i in ind..primes.size - 1) {
            var prime = primes[i];
            var nn = n * prime;
            if (nn * prime > LIM) {
                break;
            }

            factors[factInd] = prime;
            find(factInd + 1, i + 1, nn);
        }
    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            Logger.init("default.log")
            Tester.test(Task_536())
            Logger.close()
        }
    }
}
