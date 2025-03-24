package com.example

class InteractionWithLibrary(private val archive: LibraryArhiv) {
    fun takeHome(obj: LibraryObject) {
        runCatching {
            require(obj.available) { "Объект ${obj.id} недоступен!" }
            require(obj is HomeTakeable) { "Этот тип объекта нельзя брать домой!" }
            obj.markAsTaken()
            println("${obj.getType()} ${obj.id} взят домой")
        }.onFailure { println(it.message) }
    }

    fun returnObject(obj: LibraryObject) {
        runCatching {
            require(!obj.available) { "Объект ${obj.id} уже доступен!" }
            obj.markAsReturned()
            println("${obj.getType()} ${obj.id} возвращен")
        }.onFailure { println(it.message) }
    }

    fun readInLibrary(obj: LibraryObject) {
        runCatching {
            require(obj.available) { "Объект ${obj.id} недоступен!" }
            require(obj is LibraryReadable) { "Этот тип объекта нельзя читать в зале!" }
            obj.markAsTaken()
            println("${obj.getType()} ${obj.id} взят для чтения в зале")
        }.onFailure { println(it.message) }
    }

    fun manageObjects(objects: List<LibraryObject>) {
        objects.forEachIndexed { index, obj ->
            println("${index + 1}. ${obj.getInfoMini()}")
        }
        print("Выберите объект: ")
        val choice = objects.getOrNull(readLine()?.toIntOrNull()?.minus(1) ?: -1)
        if (choice == null) {
            println("Попробуйте заново")
            return
        }
        while (true) {
            println("Выберите действие:")
            println("1. Взять домой")
            println("2. Читать в читальном зале")
            println("3. Показать более подробную информацию.")
            println("4. Вернуть")
            println("5. Оцифровать")
            println("6. Назад к выбору типа объекта")
            when (readlnOrNull()?.toIntOrNull()) {
                1 -> takeHome(choice)
                2 -> readInLibrary(choice)
                3 -> println(choice.getInfoGlobal())
                4 -> returnObject(choice)
                5 -> {
                    val digitization = DigitizationInCD()
                    when (choice) {
                        is Book -> {
                            val disk = digitization.bookToCD(choice, archive)
                            println("Книга оцифрована в диск: ${disk.name}")
                        }
                        is Newspaper -> {
                            val disk = digitization.newspaperToCD(choice, archive)
                            println("Газета оцифрована в диск: ${disk.name}")
                        }
                        else -> println("Этот объект нельзя оцифровать.")
                    }
                }
                6 -> return
                else -> println("Неверный ввод!")
            }
        }
    }
}

class ManagerShop(private val archive: LibraryArhiv) {
    fun <T : LibraryObject> buy(shop: Shop<T>): T {
        shop.showAllProducts()

        print("Выберите объект: ")
        val choice = readlnOrNull()?.toIntOrNull()?.minus(1) ?: -1

        val item = when (shop) {
            is BookShop -> shop.booksShop.getOrNull(choice)
            is DiskShop -> shop.disksShop.getOrNull(choice)
            is NewspaperShop -> shop.newspapersShop.getOrNull(choice)
            else -> null
        } as? T

        if (item == null) {
            println("Неверный выбор.")
            throw IllegalArgumentException("Неверный выбор объекта.")
        }

        when (item) {
            is Book -> archive.books.add(item)
            is Disk -> archive.disks.add(item)
            is Newspaper -> archive.newspapers.add(item)
        }
        println("Куплен объект: ${item.getInfoMini()}")
        return item
    }
}



inline fun <reified T> filterTypes(obj: List<LibraryObject>): List<T>{
    return obj.filterIsInstance<T>()
}
