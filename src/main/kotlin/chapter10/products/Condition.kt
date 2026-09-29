package chapter10.products

interface Condition {
    fun isSuitable(productCard: ProductCard): Boolean
}