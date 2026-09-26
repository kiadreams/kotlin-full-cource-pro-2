package products

import kotlinx.serialization.json.Json
import java.io.File

object ProductRepository {
    private val file = File("products.json")

    private val _products = loadProducts()

    val productCards: List<ProductCard>
        get() = _products.toList()

    private fun loadProducts(): List<ProductCard> {
        val content = file.readText()
        return Json.decodeFromString(content)
    }
}