package dogs

import observer.Observer
import kotlinx.serialization.json.Json
import observer.Observable
import java.io.File

class DogRepository private constructor() : Observable<List<Dog>> {

    init {
        println("Creating dog repository...")
    }

    private val file = File("dogs.json")

    private val _observers = mutableListOf<Observer<List<Dog>>>()
    override val observers: List<Observer<List<Dog>>>
        get() = _observers.toList()

    private val _dogs: MutableList<Dog> = loadDog()
    override val currentValue: List<Dog>
        get() = _dogs.toList()

    fun loadDog(): MutableList<Dog> {
        return Json.decodeFromString<MutableList<Dog>>(file.readText().trim())
    }

    fun saveChanges() {
        Json.encodeToString(_dogs).let { file.writeText(it) }
    }

    override fun registerObserver(observer: Observer<List<Dog>>) {
        _observers.add(observer)
        observer.onChange(currentValue)
    }

    fun addOnDogsChangeListeners(observer: Observer<List<Dog>>) {
        registerObserver(observer)
    }

    override fun unregisterObserver(observer: Observer<List<Dog>>) {
        _observers.remove(observer)
    }

    companion object {
        @Volatile
        private var instance: DogRepository? = null

        private val lock = Any()
        val correctPassword by lazy { File("dog_password.txt").readText().trim() }

        fun getInstance(password: String): DogRepository {
            if (password != correctPassword) throw IllegalArgumentException("Wrong password!")
            instance?.let { return it }
            synchronized(lock) {
                return instance ?: DogRepository().also { instance = it }
            }
        }
    }

    fun addDog(dog: Dog) {
        _dogs.maxOfOrNull { it.id }
            ?.let { _dogs.add(dog.copy(id = it + 1)) }
            ?: _dogs.add(dog.copy(id = 1))
        notifyObservers()
    }

    fun deleteDog(id: Int) {
        _dogs.removeIf { it.id == id }
            .also { if (it) notifyObservers() }
    }

}