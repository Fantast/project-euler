package euler.utils

object ScalaMath {
  def primes: Stream[Long] = {
    def nextPrime(p: List[Long], n: Long): Long =
      if (!p.exists(n % _ == 0)) n
      else nextPrime(p, n + 1)

    def sieve(firstPrime: Long, p: List[Long]): Stream[Long] = {
      lazy val pnew = firstPrime :: p
      firstPrime #:: sieve(nextPrime(pnew, firstPrime + 1), pnew)
    }

    sieve(2, Nil)
  }

  def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a%b)

  def lcm(a: Int, b: Int): Int = a/gcd(a,b)*b
}
