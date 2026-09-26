package products

interface Condition {
    fun isSuitable(productCard: ProductCard): Boolean
}