package utils.sieves

class FactorizationSieve(val mx: Int) {
    val smallestFactors = IntArray(mx + 1)

    init {
        val composite = BooleanArray(mx + 1)

        var i = 2
        while (i <= mx) {
            composite[i] = true
            smallestFactors[i] = 2
            i += 2
        }

        i = 3
        while (i <= mx) {
            if (!composite[i]) {
                smallestFactors[i] = i

                if (i <= mx/i) {
                    var j = i*i
                    while (j <= mx) {
                        if (smallestFactors[j] == 0) {
                            smallestFactors[j] = i
                            composite[j] = true
                        }
                        j += i
                    }
                }
            }
            i += 2
        }
    }

    fun primeFactors(nn: Int, factors: LongArray): Int {
        assert(nn <= mx)

        var n = nn;

        var factorsCount = 0
        var lastFactor = -1

        while (n > 1) {
            val factor = smallestFactors[n]
            if (factor != lastFactor) {
                lastFactor = factor
                factors[factorsCount++] = factor.toLong()
            }
            n /= factor
        }
        return factorsCount
    }
}
