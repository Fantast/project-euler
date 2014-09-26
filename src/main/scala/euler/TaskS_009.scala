package euler


//Answer: 31875000
object TaskS_009 extends BaseTask {
  override def solving() {
    for {
      a <- 1 to 998
      b <- a to 1000
      c = 1000 - a - b
      if (a * a + b * b == c * c)
    } yield {
      println(a + " " + b + " " + c)
      println(a*b*c)
    }
  }
}
