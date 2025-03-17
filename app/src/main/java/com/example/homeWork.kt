package com.example

fun main() {
    val library = InteractionWithLibrary()
    val books = mutableListOf(
        Book("Белые ночи", true, 12312, 320, "Достоевский Ф.М."),
        Book("Крестный отец", true, 11233, 304, "Марио Пьюзо"),
        Book("Посторонний", true, 12612, 128, "Альбер Камю")
    )
    val newspapers = mutableListOf(
        Newspaper("Инженер", true, 24535, 626),
        Newspaper("Бауманские сплетни", true, 24512, 12),
        Newspaper("Детство", true, 24131, 481)
    )
    val disks = mutableListOf(
        Disk("Мстители", true, 35625, "DVD"),
        Disk("Король Лев", true, 36789, "DVD"),
        Disk("Queen. Лучшее", true, 34123, "CD")
    )

    while (true) {
        println("Выберите тип объекта:")
        println("1. Показать книги")
        println("2. Показать газеты")
        println("3. Показать диски")
        when (readlnOrNull()?.toIntOrNull()) {
            1 -> library.manageObjects(books)
            2 -> library.manageObjects(newspapers)
            3 -> library.manageObjects(disks)
            4 -> return
            else -> println("Неверный ввод!")
        }
    }
}


