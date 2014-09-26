package euler

//Answer: 4613732
object TaskS_002 extends BaseTask {
  val n = 4000000

  override def solving() {
    println(sum(1, 2, 0))
  }

  def sum(f1: Int, f2: Int, s: Int): Int = {
    if (f2 > n) s
    else if (f2%2 == 0) sum(f2, f1 + f2, s + f2)
    else  sum(f2, f1 + f2, s)
  }
}
