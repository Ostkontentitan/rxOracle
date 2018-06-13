import io.reactivex.Single.just
import io.reactivex.rxkotlin.subscribeBy

object SeekerOfWisdom {
    fun run() {
        just(Oracle("Steve"))
            .flatMap { oracle -> oracle.ask().map { answer -> oracle.name to answer } }
            .map { (name, answer) -> "$name says $answer" }.subscribeBy(
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