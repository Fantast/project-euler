package euler

//Answer: 906609
object TaskS_004 extends BaseTask {
  override def solving() {
    println(
      (for { i <- 1 to 999; j <- i to 999} yield i*j).filter(x => x.toString.reverse == x.toString).max
    )
  }
}
