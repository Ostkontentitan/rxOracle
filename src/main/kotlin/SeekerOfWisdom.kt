import io.reactivex.rxkotlin.subscribeBy

object SeekerOfWisdom {
    fun run() {
        Oracle.stream()
            .flatMapSingle { oracle -> oracle.ask().map { answer -> oracle.name to answer } }
            .map { (name, answer) -> "$name says $answer" }.subscribeBy(
                onNext = {
                    println("\n$it")
                },
                onComplete = {
                    Runner.terminate()
                },
                onError = {
                    print(it.message)
                    Runner.terminate()
                }
            )
    }
}