import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.rxkotlin.toObservable
import java.util.*
import java.util.concurrent.TimeUnit

class Oracle(val name: String) {

    private val answer: Boolean
        get() = random.nextBoolean()

    fun question(): Single<Boolean> = Single.just(answer)
            .delay(delay, TimeUnit.SECONDS)

    companion object {
        private val random = Random()
        private val delay: Long
            get() = random.nextInt(10).toLong()

        fun stream(): Observable<Oracle> =
                (0 until random.nextInt(10)).toObservable()
                        .map { Oracle("$it") }
                        .delay(delay, TimeUnit.SECONDS )
    }
}