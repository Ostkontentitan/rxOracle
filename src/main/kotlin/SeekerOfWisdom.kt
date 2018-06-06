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
                            consensus += answer.value
                        },
                        onComplete = {
                            println(" Consensus is ${Answer.fromConsensus(consensus)}")
                            Runner.terminate()
                        }
                )
    }


    fun next() {
        Oracle.stream()
                .map { oracle ->
                    oracle.question()
                            .doOnSuccess {
                                println(" Oracle ${oracle.name} says $it")
                            }

                }
                .flatMap(Single<Answer>::toObservable)
                .map { answer ->
                    answer.value
                }
                .reduce { consensus, answerValue ->
                    consensus + answerValue
                }
                .subscribeBy { consensus: Int ->
                    println("Consensus is ${Answer.fromConsensus(consensus)}")
                    Runner.terminate()
                }
    }
}