import kotlin.math.sign

enum class Answer(val value: Int) {
    YES(1),
    NO(-1),
    MAYBE(0);

    companion object {
        fun fromConsensus(value: Int) = values().first{ it.value == value.sign}
    }
}