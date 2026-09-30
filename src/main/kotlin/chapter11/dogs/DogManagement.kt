package chapter11.dogs


fun main() {
    Display().show()
    Display().show()
    Administrator(DogRepository.getInstance("dog-shelter"))
        .work()
}
