package dogs


fun main() {
    Display().show()
    Administrator(DogRepository.getInstance("dog-shelter"))
        .work()
}
