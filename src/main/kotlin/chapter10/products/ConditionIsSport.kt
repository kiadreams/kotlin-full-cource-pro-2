package chapter10.products

class ConditionIsSport : Condition {
    override fun isSuitable(productCard: ProductCard): Boolean {
        return productCard.productCategory == ProductCategory.SPORTS
    }
}