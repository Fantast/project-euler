package opener15

import euler.BaseTask

//Answer:
object Task_24s extends BaseTask {
  def f(): Long = {
    var (s, i) = (0L, Int.MinValue)
    while (i < Int.MaxValue) {
//      var c = some(i)

      var c = 1
      s += c
      i += 1
    }
    println(i)
    println(s)
    s
  }

  def some(i: Int): Long = {
//    println(i.toBinaryString + " -------------")
    var (c, n) = (1, i)
    while ({
//      println("Iteration " + c + ": " + n.toBinaryString)
      n = ((n : Int) => {
            if (n == 0) {
              ~0
            } else {
              val m : Long = (n | (n - 1)) + 1
              val a = ((m & -m) / (n & -n)) >> 1
              val b = a - 1
              val nn = (m | b).toInt

//              println(m.toBinaryString)
//              println(a.toBinaryString)
//              println(b.toBinaryString)
//              println(nn.toBinaryString)
              nn
            }
          })(n)

      n != ~0
    }) {
      c += 1
    }
    c
  }

  override def solving(): Unit = {
    println("go")

    println(some(0))
    println(some(0xFFFFFFFF))
    println(some(0xFFFFFFF))

//    some(1)
//    some(15<<28)

//    (Int.MinValue to Int.MinValue + 100).foreach(n => {
//      println("----")

//      println(n.toBinaryString + ": " + some(n))
//      println(n.toBinaryString + ": " + (n & -n).toBinaryString)

//      val m = (n | (n - 1)) + 1
//      val a : Long = ((m & -m) / (n & -n)) >> 1
//      val b = a - 1
//      var nn = (m | b).toInt
//      println(n.toBinaryString)
//      println(m.toBinaryString)
//      println(a.toBinaryString)
//      println(b.toBinaryString)
//      println(nn.toBinaryString)
//    })
    f()
  }
}
