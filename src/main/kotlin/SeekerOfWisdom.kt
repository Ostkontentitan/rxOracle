import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy

object SeekerOfWisdom {
    fun run() {
        val questions = (0 until 5).map { index ->
            Oracle("$index").run {
                question().doOnSuccess { answer ->
                            println(" Oracle $name says $answer")
                        }
            }
        }

        var consensus = 0

        Single.merge(questions)
                .subscribeBy(
                        onNext = { answer ->
                            consensus += (if (answer) 1 else -1)
                        },
                        onComplete = {
                            println(" Consensus is ${consensus >= 0}")
                            Runner.terminate()
                        }
                )
    }


    fun next() {
        Oracle.stream()
                .map(Oracle::question)
                .flatMap(Single<Boolean>::toObservable)
                .map { answer ->
                    if (answer) 1 else -1
                }
                .reduce { consensus, answerValue ->
                    consensus + answerValue
                }
                .subscribeBy{consensus: Int ->
                    println("Consensus is ${consensus >= 0}")
                    Runner.terminate()
                }
    }
}