package observer

fun interface Observer<T> {

    fun onChange(newElements: T)

}