package com.example

fun main() {
    val archive = LibraryArhiv()
    val library = InteractionWithLibrary(archive)
    val managerShop = ManagerShop(archive)

    val bookShop = BookShop()
    val diskShop = DiskShop()
    val newspaperShop = NewspaperShop()

    while (true) {
        println("Выберите тип объекта:")
        println("1. Показать книги")
        println("2. Показать газеты")
        println("3. Показать диски")
        println("4. Перейти в магазин")
        println("5. Выход")
        when (readlnOrNull()?.toIntOrNull()) {
            1 -> library.manageObjects(archive.books)
            2 -> library.manageObjects(archive.newspapers)
            3 -> library.manageObjects(archive.disks)
            4 -> {
                println("Выберите магазин:")
                println("1. Магазин книг")
                println("2. Магазин дисков")
                println("3. Газетный ларек")
                when (readlnOrNull()?.toIntOrNull()) {
                    1 -> managerShop.buy(bookShop)
                    2 -> managerShop.buy(diskShop)
                    3 -> managerShop.buy(newspaperShop)
                    else -> println("Неверный ввод!")
                }
            }
            5 -> return
            else -> println("Неверный ввод!")
        }
    }
}