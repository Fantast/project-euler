package tasks

import utils.log.Logger

//Answer :
class Task_ : AbstractTask() {
    override fun solving() {
    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            Logger.init("default.log")
            Tester.test(Task_())
            Logger.close()
        }
    }
}
