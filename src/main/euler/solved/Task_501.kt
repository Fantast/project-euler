package solved

import tasks.AbstractTask
import tasks.Tester
import utils.MyMath
import utils.log.Logger
import java.util.*

//Answer : 197912312715
class Task_501 : AbstractTask() {
    val LIM = 1000000000000L
    //    val LIM = 10000000000L
    //    val LIM = 1000000L

    val LIM3 = Math.pow(LIM.toDouble(), 1.0/3.0).toLong()

    val primes = MyMath.primes(1000000)
    val pi = LongArray(primes.last().toInt() + 1)

    var res = 0L

    override fun solving() {
        //might be related to 521 (see https://projecteuler.net/thread=521)
        //also https://projecteuler.net/thread=10;page=5 by Lucy_Hedgehog

        pi[1] = 0
        pi[2] = 1
        for (ind in 1..primes.size-1) {
            val p = primes[ind]
            val pprev = primes[ind - 1]

            val end = min(p-1, pi.size - 1L);

            for (i in pprev..end) {
                pi[i.toInt()] = ind.toLong();
            }

            if (p >= pi.size) {
                break
            }
        }

        count7()
        count42()
        count222()
        println(res)
    }

    private fun count222() {
        println("Counting 222")
        for ((index, a) in primes.withIndex()) {
            progress(index.toLong())
            if (a > LIM3) {
                break
            }

            val maxab = LIM/a
            val maxab2 = Math.sqrt(maxab.toDouble()).toLong()

            for (bi in (index+1)..primes.size - 1) {
                val b = primes[bi]
                if (b > maxab2) {
                    break
                }
                val maxc = maxab/b

                res += primeCount(maxc) - (bi+1)
            }
        }
    }

    private fun count42() {
        println("Counting 42")
        for ((index, a) in primes.withIndex()) {
            progress(index.toLong())
            if (a > LIM3) {
                break
            }

            res += primeCount(LIM/a/a/a, a)
        }
    }

    private fun count7() {
        for (a in primes) {
            val a7 = MyMath.pow(a, 7);
            if (a7 > LIM) {
                break
            }
            res++;
        }
    }

    private fun primeCount(n: Long, a: Long): Long {
        val pc = primeCount(n)
        return if (a > n || a == -1L) pc else pc - 1;
    }

    private fun primeCount(n: Long): Long {
        if (n < pi.size) {
            return pi[n.toInt()];
        }

        //        return phiPrimeCount(n);
        return externalPrimeCount(n);
    }

    //https://github.com/kimwalisch/primecount
    fun externalPrimeCount(n: Long): Long {
        val process = Runtime.getRuntime().exec("primecount $n")
        val sc: Scanner = Scanner(process.inputStream)
        return sc.nextLong()
    }

    fun phiPrimeCount(n: Long): Long {
        val sqn = Math.sqrt(n.toDouble()).toLong()
        val piSqn = primeCount(sqn).toInt();

        return piSqn + phi(n, piSqn) - 1;
    }

    //https://github.com/kimwalisch/primecount
    //http://projecteuclid.org/euclid.ijm/1255455259
    val cache: Array<HashMap<Long, Long>> = Array(10, { HashMap<Long, Long>(10000) })
    fun phi(u: Long, v: Int): Long {
        if (v == 0) {
            return u
        }
        if (u == 0L) {
            return 0L
        }
        val prime = primes[v - 1];
        if (u < prime) {
            return 1
        }

        if (v <= cache.size) {
            val vcache = cache[v - 1]
            var r = vcache[u];
            if (r == null) {
                r = phi(u, v - 1) - phi(u / prime, v - 1)
                vcache[u] = r
            }
            return r
        }

        return phi(u, v - 1) - phi(u / prime, v - 1)
    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            Logger.init("default.log")
            Tester.test(Task_501())
            Logger.close()
        }
    }
}
