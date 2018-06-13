
import io.reactivex.rxkotlin.subscribeBy
import kotlin.math.sign

object SeekerOfWisdom {
    fun run() {
        Oracle.stream()
            .concatMapSingle { it.ask() } // this part changed to concat for 4b
            .map {
                when (it) {
                    Answer.YES -> 1
                    Answer.NO -> -1
                    Answer.MAYBE -> 0
                }
            }
            .reduce(0, { sum, answer -> sum + answer })
            .map {
                when (it.sign) {
                    1 -> Answer.YES
                    -1 -> Answer.NO
                    0 -> Answer.MAYBE
                    else -> throw IllegalStateException("signed result should not deviate from -1,0,1")
                }
            }
            .map { "The oracles answer with: $it" }
            .subscribeBy(
                onSuccess = {
                    println("\n$it")
                    Runner.terminate()
                },
                onError = {
                    print(it.message)
                    Runner.terminate()
                }
            )
    }
}