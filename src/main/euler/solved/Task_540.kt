package solved

import tasks.AbstractTask
import tasks.Tester
import utils.log.Logger
import utils.sieves.FactorizationSieve

//Answer : 500000000002845
class Task_540 : AbstractTask() {

    val LIM = 3141592653589793L
    val LIM2 = LIM / 2
    val sqLIM = Math.sqrt(LIM.toDouble()).toInt()

    var factors: LongArray = LongArray(20)
    var factorsCount = 0

    override fun solving() {
        val sieve = FactorizationSieve(sqLIM)

        var res = 0L;
        var m = 1L;
        while (m <= sqLIM) {
            progress1000000(m);

            val maxn = min(
                    m - 1,
                    LIM2 / m,
                    Math.sqrt((LIM - m * m).toDouble()).toLong()
            )

            if (maxn > 0) {
                factorsCount = sieve.primeFactors(m.toInt(), factors)
                if (m % 2 == 0L) {
                    res += phi(maxn, factorsCount)
                } else {
                    res += phi(maxn / 2, factorsCount)
                }
            }
            ++m;
        }
        println(res)
    }

    //http://projecteuclid.org/euclid.ijm/1255455259
    fun phi(x: Long, v: Int): Long {
        if (v == 0) {
            return x
        }
        if (x == 0L) {
            return 0L
        }
        return phi(x, v - 1) - phi(x / factors[v - 1], v - 1)
    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            Logger.init("default.log")
            Tester.test(Task_540())
            Logger.close()
        }
    }
}
