package tasks

import utils.MyMath
import utils.log.Logger
import java.util.*

//Answer :
class Task_501 : AbstractTask() {
    val LIM = 1000000000000L
//    val LIM = 1000000L
    val LIM2 = Math.sqrt(LIM.toDouble()).toLong()
    val LIM3 = Math.pow(LIM.toDouble(), 1.0/3.0).toLong()

    val primes = MyMath.primes(5761455) //primes below 10^8
    var res = 0L
    var pcCalls = 0L

    override fun solving() {
        println(primes.size)
        count7()
        count42()
        count222()
        println(res)
        println(pcCalls)
//        println(all)
    }

    private fun count222() {
        println("Counting 222")
        for ((index, a) in primes.withIndex()) {
            progress100(index.toLong())
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

//                for (ci in (bi+1)..primes.size - 1) {
//                    val c = primes[ci]
//                    if (c > maxc) {
//                        break
//                    }
//                    ++res
//                }
            }
        }
    }

    private fun count42() {
        println("Counting 42")
        for (a in primes) {
            if (a > LIM3) {
                break
            }

            res += primeCount(LIM/a/a/a, a)
        }
    }

    val all: HashSet<Long> = HashSet<Long>()
    private fun primeCount(n: Long, vararg exclude: Long): Long {
        if (n > 100000000) {
//            all.add(n);
            pcCalls++;
        }
        return 1;
//        var count = 0L;
//        for (b in primes) {
//            if (b in exclude) continue;
//            if (b > n) {
//                break
//            }
//            count++;
//        }
//        return count
    }

    private fun count7() {
        println("Counting 7")
        for (a in primes) {
            val a7 = MyMath.pow(a, 7);
            if (a7 > LIM) {
                break
            }
            res++;
        }
    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            Logger.init("default.log")
            Tester.test(Task_501())
            Logger.close()
        }
    }
}
