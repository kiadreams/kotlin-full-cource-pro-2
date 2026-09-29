package chapter10.products

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ProductCategory {
    @SerialName("Electronics")
    ELECTRONICS,

    @SerialName("Clothing")
    CLOTHING,

    @SerialName("Home goods")
    HOME_GOODS,

    @SerialName("Beauty")
    BEAUTY,

    @SerialName("Sports")
    SPORTS
}