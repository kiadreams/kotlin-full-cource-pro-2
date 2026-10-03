package chapter11.dogs

import kotlinx.serialization.json.Json
import chapter11.observer.MutableObservable
import chapter11.observer.Observable
import java.io.File

class DogRepository private constructor() {

    init {
        println("Creating dog repository...")
    }

    private val file = File("chapter11.dogs.json")

    private val _dogs: MutableList<Dog> = loadDog()


    val dogs: Observable<List<Dog>>
        field = MutableObservable(_dogs.toList())

    fun loadDog(): MutableList<Dog> {
        return Json.decodeFromString<MutableList<Dog>>(file.readText().trim())
    }

    fun saveChanges() {
        Json.encodeToString(_dogs).let { file.writeText(it) }
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
        Thread.sleep(3000)
        _dogs.maxOfOrNull { it.id }
            ?.let { _dogs.add(dog.copy(id = it + 1)) }
            ?: _dogs.add(dog.copy(id = 1))
        dogs.currentValue = _dogs.toList()
    }

    fun deleteDog(id: Int) {
        Thread.sleep(3000)
        _dogs.removeIf { it.id == id }
            .also { if (it) dogs.currentValue = _dogs.toList() }
    }
}