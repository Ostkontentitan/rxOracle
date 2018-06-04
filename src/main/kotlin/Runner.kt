import java.util.concurrent.atomic.AtomicBoolean

object Runner {
    val running = AtomicBoolean(true)

    @JvmStatic
    fun main(args: Array<String>) {
        while (running.get()) {
            Animation.nextFrame()
            Thread.sleep(300)
        }
    }
}

private object Animation {
    private const val BASE = "(---------)"
    private const val INNER_LENGTH = 10

    private var frame = 0L

    fun nextFrame() {
        val index = (frame % INNER_LENGTH + 1).toInt()
        val progress = BASE.replaceRange(index, index, "*")

        print("\r$progress")
        frame++
    }
}