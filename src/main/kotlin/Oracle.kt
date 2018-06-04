import io.reactivex.Single
import java.util.*
import java.util.concurrent.TimeUnit

class Oracle(val name: String) {
    private val delay: Long
        get() = random.nextInt(10).toLong()
    private val answer: Boolean
        get() = random.nextBoolean()

    fun question(): Single<Boolean> = Single.just(answer)
            .delay(delay, TimeUnit.SECONDS)

    private companion object {
        val random = Random()
    }
}