package chapter11.dogs

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
enum class Breed {
    @SerialName("Labrador Retriever") LABRADOR_RETRIEVER,
    @SerialName("German Shepherd") GERMAN_SHEPHERD,
    @SerialName("Golden Retriever") GOLDEN_RETRIEVER,
    @SerialName("Bulldog") BULLDOG
}