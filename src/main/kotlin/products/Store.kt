package products


fun main() {
    ProductRepository.productCards
        .also { println("Фильтруем по категории - Одежда") }
        .filter { it.productCategory == ProductCategory.CLOTHING }
        .also { println("Увеличиваем ценник в 2 раза") }
        .map { it.copy(productPrice = it.productPrice * 2) }
        .also { println("Создаем список строк: id - name - price") }
        .map { "${it.id} - ${it.productName} - ${it.productPrice}" }
        .also { println("Выводим каждый элемент...") }
        .forEach { println(it) }
}