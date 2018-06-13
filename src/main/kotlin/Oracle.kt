import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.Single.just
import io.reactivex.rxkotlin.zipWith
import java.util.Random
import java.util.concurrent.TimeUnit

class Oracle(val name: String) {

    fun ask(): Single<Answer> = just(randomAnswer()).delay(delay(), TimeUnit.SECONDS)

    companion object {
        private val random = Random()

        private fun delay(): Long = random.nextInt(5).toLong()

        private fun randomAnswer() = random.nextInt(2).let { Answer.values()[it] }

        fun stream(gong: Observable<Unit>): Observable<Oracle> = Observable.range(1, 5).zipWith(gong).map { Oracle("Steve ${it.first}") }
    }
}