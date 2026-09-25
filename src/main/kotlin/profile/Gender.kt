package org.example.profile

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Gender {
    @SerialName("Male")
    Male,

    @SerialName("Female")
    Female
}