package extensions


inline fun <T, R> Iterable<T>.transform(operation: (T) -> R): List<R> {
    return this.map { operation(it) }
}

inline fun <T> Iterable<T>.myFilter(isSuitable: (T) -> Boolean): List<T> {
    return this.filter { isSuitable(it) }
}

inline fun <T> Iterable<T>.myForEach(operation: (T) -> Unit) {
    for (element in this) operation(element)
}

inline fun <T, R> T.myLet(block: (T) -> R): R {
    return block(this)
}

inline fun <T> T.myAlso(block: (T) -> Unit): T {
    block(this)
    return this
}

inline fun <T> T.myApply(block: T.() -> Unit): T {
    block()
    return this
}

inline fun <T, R> myWith(receiver: T, block: T.() -> R): R {
    return receiver.block()
}