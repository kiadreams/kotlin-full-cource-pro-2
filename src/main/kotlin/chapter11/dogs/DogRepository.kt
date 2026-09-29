package chapter11.dogs

import kotlinx.serialization.json.Json
import java.io.File

class DogRepository private constructor() {

    init {
        println("Creating dog repository...")
    }

    private val file = File("chapter11.dogs.json")
    private val _dogs: MutableList<Dog> = loadDog()
    val dogs: List<Dog>
        get() = _dogs.toList()

    fun loadDog(): MutableList<Dog> {
        return Json.decodeFromString<MutableList<Dog>>(file.readText().trim())
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
}