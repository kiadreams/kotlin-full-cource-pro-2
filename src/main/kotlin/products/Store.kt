package products


fun main() {
    val products = ProductRepository.productCards
    var filtered = filter(products) { it.productCategory == ProductCategory.CLOTHING }
    filtered = transform(filtered) { it.copy(productPrice = it.productPrice * 2) }
    transform(filtered) { "${it.id} - ${it.productName} - ${it.productPrice}" }
        .forEach { println(it) }
}

fun <R> transform(products: List<ProductCard>, operation: (ProductCard) -> R): List<R> {
    return products.map { operation(it) }
}

fun filter(products: List<ProductCard>, isSuitable: (ProductCard) -> Boolean): List<ProductCard> {
    return products.filter { isSuitable(it) }
}