package chapter11.observer

class MutableObservable<T>(initialValue: T) : Observable<T> {


    override var currentValue: T = initialValue
        set(newValue: T) {
            field = newValue
            notifyObservers()
        }

    private var _observers = mutableListOf<Observer<T>>()
    override val observers: List<Observer<T>>
        get() = _observers.toList()

    override fun registerObserver(observer: Observer<T>) {
        _observers.add(observer)
        observer.onChange(currentValue)
    }

    override fun unregisterObserver(observer: Observer<T>) {
        _observers.remove(observer)
    }
}