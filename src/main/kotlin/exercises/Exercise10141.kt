package exercises

fun main() {

}


fun formatText(title: String, body: List<String>, footer: String): String {
    require(title.isNotBlank()) { "Title must not be blank" }
    require(body.isNotEmpty()) { "Body must contain at least one paragraph" }
    require(footer.isNotBlank()) { "Footer must not be blank" }

    val debugKeyword = "debug"
    val oldHeader = "=== Начало текста ==="
    val newHeader = "=== Новый заголовок ==="
    val maxTextLength = 500

    return with(StringBuilder()) {

        append("=== $title ===\n")
        body.forEach { append("$it\n") }
        append("--- $footer ---\n")

        insert(0, "\n$oldHeader\n")
        append("\n=== Конец текста ===")

        indexOf(debugKeyword)
            .takeIf { it != -1 }
            ?.let { delete(it, it + debugKeyword.length) }

        indexOf(oldHeader)
            .takeIf { it != -1 }
            ?.let { replace(it, it + oldHeader.length, newHeader) }

        if (length > maxTextLength) setLength(maxTextLength)

        toString()
    }
}