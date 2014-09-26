package euler

import utils.ScalaMath._

//Answer: 232792560
object TaskS_005 extends BaseTask {
  override def solving() {
    println((1 to 20).foldLeft(1)(lcm))
  }
}
