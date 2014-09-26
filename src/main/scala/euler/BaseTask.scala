package euler

import compat.Platform
import compat.Platform._

trait BaseTask {
  def main(args: Array[String]) = {
    val executionStart = System.currentTimeMillis();
    solving()
    println("\nTask completed in: " + (Platform.currentTime - executionStart) + " ms.")
  }

  def solving()
}
