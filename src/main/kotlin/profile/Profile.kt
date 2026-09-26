package profile

fun main() {
    val people = ProfileRepository.profiles
    var filteredPeople = filter(people) { it.age > 25 }
    filteredPeople = filter(filteredPeople) { it.gender == Gender.MAlE }
    filteredPeople = filter(filteredPeople) { it.firstName.startsWith('A') }
    filteredPeople = filter(filteredPeople) { it.age < 30 }
    filteredPeople.forEach { println(it) }
    transform(filteredPeople) { it.firstName }
        .forEach { println(it) }
    transform(filteredPeople) { it.lastName }
        .forEach { println(it) }
    transform(filteredPeople) { it.age }
        .forEach { println(it) }
    transform(filteredPeople) { it.copy(age = it.age + 1) }
        .forEach { println(it) }
}

fun <R> transform(profiles: List<Person>, operation: (Person) -> R): List<R> {
    return profiles.map { operation(it) }
}

fun filter(people: List<Person>, isSuitable: (Person) -> Boolean): List<Person> {
    return people.filter { isSuitable(it) }
}