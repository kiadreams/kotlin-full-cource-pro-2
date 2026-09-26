package products

class ConditionPriceMoreThan300 : Condition {
    override fun isSuitable(productCard: ProductCard): Boolean {
        return productCard.productPrice > 300
    }
}