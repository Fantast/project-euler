package solved

import tasks.AbstractTask
import tasks.Tester
import utils.MyMath
import utils.log.Logger
import java.math.BigInteger
import kotlin.math.minus
import kotlin.math.plus
import kotlin.math.times

//Answer : 426334056
class Task_539 : AbstractTask() {

    override fun solving() {
        println(S(1000000000000000000))
        println(S(1000000000000000000) % bi(987654321))
    }

    fun P(n: Long): Long {
        if (n == 1L) {
            return 1L;
        }
        return 2*(f(n/2) + e(n/2))
    }

    fun S(n: Long): BigInteger {
        return fs(n) + es(n);
    }

    fun e(n: Long): Long {
        if (n == 1L) {
            return 1L;
        }

        val s2: Long;

        val bits = 64 - java.lang.Long.numberOfLeadingZeros(n)
        if (bits % 2 == 0) {
            val ss = (1L shl bits) - 1L;
            s2 = -(ss and 0b0101010101010101010101010101010101010101010101010101010101010101L);
        } else {
            val ss = (1L shl bits) - 1L;
            s2 = (ss and 0b010101010101010101010101010101010101010101010101010101010101010L) + 1L;
        }

        return s2;
    }

    fun f(n: Long): Long {
        if (n == 1L) {
            return 0L;
        }
        val bits = 64 - java.lang.Long.numberOfLeadingZeros(n)

        val s1 = n and 0b0101010101010101010101010101010101010101010101010101010101010101L;
        val last = 1L shl (bits - 1);

        return s1 + (if (bits % 2 == 0) last else -last);
    }

    fun fs(nn: Long): BigInteger {
        assert(nn % 2L == 0L); //for convinience

        val n = nn / 2L;

        var s = bi(0);
        for (bit in 0..61) {
            val ds = (1L shl bit)

            var cnt = 0L;
            if (bit % 2 != 0) {
                val maxn = (1L shl (bit + 1)) - 1;
                val minn = (1L shl bit);
                if (minn > n) {
                    cnt = 0;
                } else {
                    cnt = Math.min(n, maxn) - minn + 1;
                }
            } else {
                for (bits in (bit+1)..63) {
                    if (bits % 2 == 0 || (bits >= bit + 3)) {
                        val maxn = (1L shl bits) - 1;
                        val minn = (1L shl (bits - 1)) + (1L shl bit);
                        if (minn > n) {
//                            cnt += 0;
                        } else if (maxn <= n) {
                            cnt += 1L shl (bits - 2);
                        } else {
                            val leftParts = (n - (1L shl (bits - 1))) shr (bit + 1);
                            val hasBit = MyMath.isBitSet(n, bit);

                            val rightParts = n % (1L shl bit)
                            if (hasBit) {
                                //numbers that has the same prefix as n
                                cnt += rightParts + 1
                            }

                            if (leftParts != 0L) {
                                //fit numbers below n
                                cnt += leftParts * (1L shl bit);
                            }
                        }
                    }
                }
            }

            s += bi(cnt) * bi(ds);
        }

        s = s * bi(4L) - bi(2*f(n))

        return s;
    }

    fun es(nn: Long): BigInteger {
        assert(nn % 2L == 0L); //for convinience

        val n = nn / 2L;

        var s = bi(1); // for 1 bit
        for (bits in 2..61) {
            val pmax = (1L shl bits) - 1L
            val pmin = (1L shl (bits - 1))

            if (pmin > n) {
                break;
            }

            val cnt = Math.min(pmax, n) - pmin + 1L;
            val s2: Long;
            if (bits % 2 == 0) {
                val ss = (1L shl bits) - 1L;
                s2 = -(ss and 0b0101010101010101010101010101010101010101010101010101010101010101L);
            } else {
                val ss = (1L shl bits) - 1L;
                s2 = (ss and 0b010101010101010101010101010101010101010101010101010101010101010L) + 1L;
            }

            s += bi(cnt) * bi(s2);
        }

        s = s * bi(4L) - bi(2*e(n)) + bi(1)

        return s;
    }

    fun P0(n: Long): Long {
        if (n == 1L) {
            return 1L;
        }

        val nn = n / 2;
        return 2 * (nn + 1 - P(nn));
    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            Logger.init("default.log")
            Tester.test(Task_539())
            Logger.close()
        }
    }
}