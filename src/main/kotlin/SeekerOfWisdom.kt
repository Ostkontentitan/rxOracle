import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy

object SeekerOfWisdom {
    fun run() {
        val questions = (0 until 5).map {
            Oracle("$it").question()
                    .doOnSuccess { answer ->
                        println(" Oracle $it says $answer")
                    }
        }

        var consensus = 0

        Single.merge(questions)
                .subscribeBy(
                        onNext = { answer ->
                            consensus += (if (answer) 1 else -1)
                        },
                        onComplete = {
                            println("Consensus is ${consensus >= 0}")
                            Runner.terminate()
                        }
                )
    }
}