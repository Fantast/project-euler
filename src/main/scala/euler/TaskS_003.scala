package euler

import scala.math
import utils.ScalaMath._

//Answer: 6857
object TaskS_003 extends BaseTask {
//  val n = 13195
  val n = 600851475143L

  override def solving() {
    def grestDiv(n: Long, div: Long, pr: Stream[Long]): Long = {
      if (n % pr.head == 0) grestDiv(n/pr.head, pr.head, pr)
      else if (pr.head > math.sqrt(n)) math.max(div, n)
      else grestDiv(n, div, pr.tail)
    }

    println(grestDiv(n, 1, primes))
  }
}
