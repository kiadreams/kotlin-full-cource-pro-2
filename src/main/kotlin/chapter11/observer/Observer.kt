package chapter11.observer

fun interface Observer<T> {
    fun onChange(newElements: T)
}