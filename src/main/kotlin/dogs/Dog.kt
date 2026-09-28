package dogs

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Dog(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("breed") val breed: Breed,
    @SerialName("weight") val weight: Double
)