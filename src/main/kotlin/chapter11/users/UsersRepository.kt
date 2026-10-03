package chapter11.users

import kotlinx.serialization.json.Json
import chapter11.observer.MutableObservable
import chapter11.observer.Observable
import java.io.File


class UsersRepository private constructor() {

    init {
        println("Creating repository")
    }

    private val file = File("chapter11.users.json")

    private val userList = loadUser()

    val users: Observable<List<User>>
        field = MutableObservable(userList.toList())

    val oldestUser: Observable<User>
        field = MutableObservable<User>(userList.maxBy { it.age })

    private fun loadUser(): MutableList<User> {
        if (!file.exists() || file.readText().isBlank()) return mutableListOf()
        return Json.decodeFromString<MutableList<User>>(file.readText().trim())
    }

    fun saveChanges() {
        Json.encodeToString(userList).let { file.writeText(it) }
    }

    companion object {

        private var instance: UsersRepository? = null
        fun getInstance(password: String): UsersRepository {
            val correctPassword = File("password_users.txt").readText().trim()
            if (password != correctPassword) throw IllegalArgumentException("Wrong password")
            if (instance == null) {
                instance = UsersRepository()
            }
            return instance!!
        }
    }

    fun addUser(user: User) {
        Thread.sleep(10_000)
        userList.maxOfOrNull { it.id }
            ?.let { userList.add(user.copy(id = it + 1)) }
            ?: userList.add(user.copy(id = 1))
        users.currentValue = userList.toList()
        if (user.age > oldestUser.currentValue.age) {
            oldestUser.currentValue = user
        }
    }

    fun deleteUser(id: Int) {
        Thread.sleep(10_000)
        userList.removeIf { it.id == id }
            .also {
                if (it) {
                    users.currentValue = userList.toList()
                }
            }
        if (userList.isNotEmpty()) {
            userList.maxBy { it.age }
                .takeIf { it != oldestUser.currentValue }
                ?.let { oldestUser.currentValue = it }
        }
    }

}