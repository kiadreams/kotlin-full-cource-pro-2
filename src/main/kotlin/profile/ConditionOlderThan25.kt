package profile

class ConditionOlderThan25 : Condition {
    override fun isSuitable(person: Person): Boolean {
        return person.age > 25
    }
}