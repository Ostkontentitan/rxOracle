import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.rxkotlin.toObservable
import java.util.*
import java.util.concurrent.TimeUnit

class Oracle(val name: String) {

    private val answer: Answer
        get() = nextAnswer()

    fun question(): Single<Answer> = Single.just(answer)
            .delay(delay, TimeUnit.SECONDS)

    private fun nextAnswer() =
            random.nextInt(2).let { Answer.values()[it] }

    companion object {
        private val random = Random()
        private val delay: Long
            get() = random.nextInt(5).toLong()

        fun stream(): Observable<Oracle> =
                (0 until random.nextInt(10)).toObservable()
                        .map { Oracle("$it") }
                        .delay(delay, TimeUnit.SECONDS )
    }
}