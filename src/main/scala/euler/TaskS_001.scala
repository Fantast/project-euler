package euler

import compat.Platform
import annotation.tailrec

//Answer: 233168
object TaskS_001 extends BaseTask {

  val n = 1000

  override def solving() {
    println(sum(1, 0))
  }

  @tailrec
  def sum(i: Int, s: Int): Int = {
    if (i >= n) s
    else if (i%5 == 0 || i%3 == 0) sum(i+1, s + i)
    else sum(i+1, s)
  }
}
