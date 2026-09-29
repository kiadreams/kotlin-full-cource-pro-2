package chapter11.users


fun main() {
    DisplayUser().show()
    Administrator(UsersRepository.getInstance("qwerty"))
        .work()
}