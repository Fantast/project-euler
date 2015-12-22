package solved

import tasks.AbstractTask
import tasks.Tester
import utils.MyMath
import utils.log.Logger
import java.math.BigInteger
import kotlin.math.plus

//Answer : 3557005261906288
class Task_536_bf : AbstractTask() {
//    val LIM = 100000L;
    val LIM = 1000000L;
//    val LIM = 5000000L;
//    val LIM = 1000000000000L;

    var sum = BigInteger("3");
    var primes: LongArray = longArrayOf();

    override fun solving() {
        MyMath.setMaxPrimesToCache(80000);
        primes = MyMath.getPrimesBetween(0, Math.sqrt(LIM.toDouble()).toLong())
        println("go")

        find(1, 1L, 1L)

        println(sum);
    }

    fun find(ind: Int, n: Long, carm: Long) {
        for (i in ind..primes.size - 1) {
            if (ind == 1) {
                progress(i.toLong());
            }

            var prime = primes[i];
            var nn = n * prime;
            if (nn > LIM) {
                break;
            }

            var cn = MyMath.lcm(carm, prime - 1)

            if ((nn + 3) % cn == 0L) {
                sum += bi(nn);
            }

            find(i + 1, nn, cn);
        }
    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            Logger.init("default.log")
            Tester.test(Task_536_bf())
            Logger.close()
        }
    }
}
