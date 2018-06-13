import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.Single.just
import java.util.Random
import java.util.concurrent.TimeUnit

class Oracle(val name: String) {

    fun ask(): Single<Answer> = just(randomAnswer()).delay(delay(), TimeUnit.SECONDS)

    companion object {
        private val random = Random()

        private fun delay(): Long = random.nextInt(5).toLong()

        private fun randomAnswer() = random.nextInt(2).let { Answer.values()[it] }

        fun stream(): Observable<Oracle> = TODO("Challenge 3")
    }
}