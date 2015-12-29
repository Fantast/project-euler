package utils.sieves

import org.junit.Assert
import org.junit.Test

class FactorizationSieveTest {

    @Test
    fun testSieve() {
        val sieve = FactorizationSieve(1000)
        val factors = LongArray(20)

        var factorsCount = sieve.primeFactors(55, factors)
        Assert.assertEquals(2, factorsCount)
        Assert.assertEquals(5, factors[0])
        Assert.assertEquals(11, factors[1])

        factorsCount = sieve.primeFactors(125*4, factors)
        Assert.assertEquals(2, factorsCount)
        Assert.assertEquals(2, factors[0])
        Assert.assertEquals(5, factors[1])

        factorsCount = sieve.primeFactors(1, factors)
        Assert.assertEquals(0, factorsCount)
    }
}