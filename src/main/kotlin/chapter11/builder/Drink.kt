package chapter11.builder

data class Drink(
    val type: String,
    val additives: List<String>,
    val diningOption: String,
    val temperature: String
) {
    class Builder {
        private var type: String = ""
        private var additives: List<String> = listOf()
        private var diningOption: String = ""
        private var temperature: String = ""

        fun type(type: String) = apply { this.type = type }
        fun additives(additives: List<String>) = apply { this.additives = additives }
        fun diningOption(diningOption: String) = apply { this.diningOption = diningOption }
        fun temperature(temperature: String) = apply { this.temperature = temperature }

        fun build() = Drink(type, additives, diningOption, temperature)
    }
}