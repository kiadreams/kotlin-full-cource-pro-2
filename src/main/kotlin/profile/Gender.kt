package profile

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Gender {
    @SerialName("Male")
    MAlE,

    @SerialName("Female")
    FEMALE
}