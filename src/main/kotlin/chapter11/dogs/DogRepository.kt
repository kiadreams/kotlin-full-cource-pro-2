package chapter11.dogs

import chapter11.observer.Observer
import kotlinx.serialization.json.Json
import java.io.File

class DogRepository private constructor() {

    init {
        println("Creating dog repository...")
    }

    private val observers = mutableListOf<Observer<List<Dog>>>()
    private val file = File("dogs.json")
    private val _dogs: MutableList<Dog> = loadDog()
    val dogs: List<Dog>
        get() = _dogs.toList()

    fun loadDog(): MutableList<Dog> {
        return Json.decodeFromString<MutableList<Dog>>(file.readText().trim())
    }

    fun saveChanges() {
        Json.encodeToString(_dogs).let { file.writeText(it) }
    }

    fun addOnDogsChangeListeners(observer: Observer<List<Dog>>) {
        observers.add(observer)
        observer.onChange(dogs)
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
        notifyObserver()
    }

    fun deleteDog(id: Int) {
        _dogs.removeIf { it.id == id }
            .also { if (it) notifyObserver() }
    }

    fun notifyObserver() {
        observers.forEach { it.onChange(dogs) }
    }
}