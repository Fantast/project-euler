package solved

import tasks.AbstractTask
import tasks.Tester
import utils.MyMath
import utils.log.Logger

//Answer : 789453601
class Task_533 : AbstractTask() {
//    val LIM = 100
    val LIM = 20000000

    val MOD = 1000000000L;
    val mxLog = DoubleArray(LIM)
    val mxMod = LongArray(LIM)

    override fun solving() {
        MyMath.setMaxPrimesToCache(2000000);
        val primes = MyMath.getPrimesBetween(0, LIM.toLong());

        for (n in LIM-2 downTo 1 step 2) {
            val max2pow = java.lang.Long.lowestOneBit(n.toLong())
            val cn2 = when (max2pow) {
                1L -> throw IllegalStateException("")
                else -> max2pow * 4
            }
            mxLog[n] = Math.log(cn2.toDouble())
            mxMod[n] = cn2
        }

        for (p in primes) {
            if (p != 2L) {
                val plog = Math.log(p.toDouble())
                val p1 = (p - 1).toInt()

                var n = p1
                while (n < LIM) {
                    mxLog[n] += plog
                    mxMod[n] = (mxMod[n] * p) % MOD

                    var pp = p
                    while (n % pp == 0L) {
                        mxLog[n] += plog
                        mxMod[n] = (mxMod[n] * p) % MOD
                        pp *= p
                    }

                    n += p1
                }
            }
        }

        var r = 0L
        var rl = 0.0
        for ((i, el) in mxLog.withIndex()) {
            if (el > rl) {
                rl = el
                r = mxMod[i]
            }
        }
        println(r + 1)
    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            Logger.init("default.log")
            Tester.test(Task_533())
            Logger.close()
        }
    }
}
