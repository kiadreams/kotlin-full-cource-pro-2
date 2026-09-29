package chapter10.profile

class ConditionGenderIsMale : Condition {
    override fun isSuitable(person: Person): Boolean {
        return person.gender == Gender.MAlE
    }
}