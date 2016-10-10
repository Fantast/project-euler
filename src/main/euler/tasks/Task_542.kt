package tasks

import utils.LongFraction
import utils.log.Logger

//Answer :
class Task_542 : AbstractTask() {
    override fun solving() {
        var prevs = 0
        var r = 0
        for (n in 4..1000) {
            prevs = solve(n, prevs)
            r += if (n%2==0) prevs else -prevs
            if (n%1000 == 0) {
                println("$n: $r")
            }
        }
        println(r)
    }

    private fun solve(n: Int, prevs: Int): Int {
        var bests = 0
        var besta = 0
        var bestb = 0
        for (a in 1..n) {
            for (b in a + 1..n) {
                var s = a + b
                var c = b * b

                while (c % a == 0) {
                    c /= a
                    if (c > n) {
                        break
                    }
                    s += c
                    c *= b
                }

                if (s > bests && s > a+b) {
                    bests = s
                    besta = a
                    bestb = b
                }
            }
        }

//        if (prevs != bests && n == besta*besta) {
        if (prevs != bests) {
//        if (prevs == -1) {
            var a = besta
            var b = bestb
            var c = b * b / a
            var f = LongFraction(b.toLong(), a.toLong())

//            if (f.numer*f.numer != n.toLong()) {
//                return bests
//            }

            System.out.printf("%4d = %4d | %5s | %d + %d", n, bests, f.toString(), a, b)
//            System.out.printf("$n = $bests | $f | $a + $b")
//            print("$n = $bests | $f | $a + $b")
            while (c <= n) {
                print(" + $c")
                if (c * b % a != 0) {
                    break
                }
                c = c * b / a
            }
            println()
        }
        return bests
    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            Logger.init("default.log")
            Tester.test(Task_542())
            Logger.close()
        }
    }
}
