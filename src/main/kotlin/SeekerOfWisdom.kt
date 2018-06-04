import io.reactivex.Completable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

object SeekerOfWisdom {
    fun run() {
        for (i in 0 until 5) {
            Oracle("$i").apply {
                question()
                        .observeOn(Schedulers.computation())
                        .subscribeBy(
                                onSuccess = { println(" Oracle $name says $it") }
                        )
            }

        }

        Completable.complete()
                .delay(10, TimeUnit.SECONDS)
                .subscribe(Runner::terminate)
    }
}