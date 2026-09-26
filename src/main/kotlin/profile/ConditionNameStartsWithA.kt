package profile

class ConditionNameStartsWithA : Condition {
    override fun isSuitable(person: Person): Boolean {
        return person.firstName.startsWith('A')
    }
}