package products

class ConditionIsHighRating : Condition {
    override fun isSuitable(productCard: ProductCard): Boolean {
        return productCard.productRating > 3
    }
}