package solved

import tasks.AbstractTask
import tasks.Tester
import utils.MyMath
import utils.log.Logger
import java.util.*

//Answer : 44389811
class Task_521_bf : AbstractTask() {

    //    //http://www.wolframalpha.com/input/?i=sum+primes+below+1000000000000
    val S10_12 = bi("18435588552550705911377")
    //    //http://www.wolframalpha.com/input/?i=sum+primes+below+1000000
    val S10_6 = bi("37550402023")
    //http://www.wolframalpha.com/input/?i=sum+primes+below+100
    val S100 = bi("1060")
    //http://www.wolframalpha.com/input/?i=sum+primes+below+10
    val S10 = bi("17")

        val LIM = 1000000000000L
//    val LIM = 222L

    val MOD = 1000000000L
    val bMOD = bi(MOD)

    val segmentSize = 100000000
    val segmentSizeL = segmentSize.toLong()
    val primes = MyMath.primes(80000)

    override fun solving() {
        var s = 0L

        var sBeg = 0L
        var sEnd = Math.min(LIM, segmentSizeL)
        var segSize = sEnd - sBeg
        var isComposite = BooleanArray(segmentSize + 1)

        var sumSmallPrimes = 0L
        for (p in primes) {
            if (p*p > sEnd) {
                break
            }
            sumSmallPrimes = sumSmallPrimes + p
        }
        println("---")

        while (sBeg < LIM) {
            progress(sBeg)

            val sqEnd = Math.sqrt(sEnd.toDouble()).toLong()
            for (p in primes) {
                if (p > sqEnd) {
                    break
                }

                var cnt = 0L
                val pi = p.toInt()
                var pStart = (p - sBeg % p).toInt()
                while (pStart <= segSize) {
                    if (!isComposite[pStart]) {
                        isComposite[pStart] = true
                        ++cnt
                    }
                    pStart += pi
                }
                s = (s + p * cnt) % MOD
            }

            Arrays.fill(isComposite, false)
            sBeg = sEnd
            sEnd = Math.min(LIM, sEnd + segmentSizeL)
            segSize = sEnd - sBeg
        }

        println((bi(s) + S10_12 - bi(sumSmallPrimes)) % bMOD)
    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            Logger.init("default.log")
            Tester.test(Task_521_bf())
            Logger.close()
        }
    }
}
