package com.example

class Book(name: String, available: Boolean, id: Int, val pages: Int, val creater: String) :
    LibraryObject(name, available, id), HomeTakeable, LibraryReadable  {

    override fun getInfoGlobal() =
        "книга $name ($pages стр.) автора $creater с id:$id доступна: " +
                "${if (available) "Да" else "Нет"}"

    override fun getType() = "Книга"

    override fun takeHome() {
        println("Книга $id взята домой")
    }

    override fun readHere() {
        println("Книга $id читается в библиотеке")
    }
}

class BookShop : Shop<Book> {
    val booksShop = listOf(
        Book("1984", true, 12441, 328, "Джордж Оруэлл"),
        Book("Мастер и Маргарита", true, 12943, 480, "Михаил Булгаков"),
        Book("Преступление и наказание", true, 12360, 672, "Федор Достоевский")
    )
    private var currentIndex = 0

    override fun sell(): Book {
        val book = booksShop[currentIndex]
        currentIndex = (currentIndex + 1) % booksShop.size
        return book
    }

    override fun showAllProducts() {
        println("Доступные книги:")
        booksShop.forEachIndexed { index, book ->
            println("${index + 1}. ${book.getInfoMini()}")
        }
    }
}
