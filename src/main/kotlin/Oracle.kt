import io.reactivex.Observable
import java.util.Random

class Oracle(val name: String) {

    fun ask(): Unit = TODO("Challenge 1")

    companion object {
        private val random = Random()

        private fun delay(): Long = random.nextInt(5).toLong()

        private fun randomAnswer() = random.nextInt(2).let { Answer.values()[it] }

        fun stream(): Observable<Oracle> = TODO("Challenge 3")
    }
}