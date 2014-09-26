package euler

//Answer: 25164150
object TaskS_006 extends BaseTask {
  override def solving() {
    val ns = 1 to 100
    println(ns.sum * ns.sum - ns.map(x => x*x).sum)
  }
}
