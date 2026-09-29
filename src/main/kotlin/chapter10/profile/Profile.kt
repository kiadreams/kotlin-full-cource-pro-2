package chapter10.profile


fun main() {
    showEmail()
}

fun filterCollection() {
    ProfileRepository.profiles
        .filter { it.age > 25 }
        .filter { it.gender == Gender.MAlE }
        .filter { it.firstName.startsWith('A') }
        .filter { it.age < 30 }
        .toSet()
        .map { it.copy(age = it.age + 1) }
        .sortedByDescending { it.age }
        .forEach(::println)
}

fun showEmail() {
    print("Enter person's id: ")
    val personId = readln().toInt()
    ProfileRepository.profiles
        .find { it.id == personId }
        ?.let { println(it.email) } ?: println("Not found")
}