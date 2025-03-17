package com.example

abstract class LibraryObject(val name: String, var available: Boolean, val id: Int) {
    abstract fun getInfoGlobal(): String
    fun getInfoMini(): String {
        return "$name доступна: ${if (available) "Да" else "Нет" }"
    }
}

class Book(name: String, available: Boolean, id: Int, val pages: Int, val creater: String): LibraryObject(name,available,id) {
    override fun getInfoGlobal(): String {
        return "книга $name ($pages стр.) автора $creater с id:$id доступен: ${if (available) "Да" else "Нет" }"
    }
}

class Newspaper(name: String, available: Boolean, id: Int, val numberOfSerial: Int): LibraryObject(name,available,id) {
    override fun getInfoGlobal(): String {
        return "выпуск: $numberOfSerial газеты $name с id:$id доступен: ${if (available) "Да" else "Нет" }"
    }
}

class Disk(name: String, available: Boolean, id: Int, val types: String): LibraryObject(name,available,id){
    override fun getInfoGlobal(): String {
        return "$types $name доступее: ${if (available) "Да" else "Нет" }"
    }
}

class InteractionWithLibrary {
    fun takeHome(Object: LibraryObject) {
        runCatching {
            require(Object !is Newspaper) { "Действие невозможно. Газеты нельзя брать домой!" }
            require(Object.available) { "Этот объект недоступен!" }
            Object.available=false
            println("${if (Object is Book) "Книга" else "Диск" }  ${Object.id} забирают домой")
        }.onFailure { println(it.message) }
    }

    fun returnObject(Object: LibraryObject){
        runCatching {
            require(!Object.available) { "Объект незачем возвращать, ведь он и так у нас" }
            Object.available=true
            println("Спасибо, что вернули!")
        }.onFailure { println(it.message) }
    }

    fun readingHere(Object: LibraryObject){
        runCatching {
            require(Object !is Disk) { "Действие невозможно. Диски не слушают в читальном зале!" }
            require(Object.available) { "Этот объект недоступен!" }
            Object.available=false
            println("${if (Object is Book) "Книга" else "Газета" }  ${Object.id} берут  в читальный зал")
        }.onFailure { println(it.message) }
    }

    fun managementObject(Object: List<LibraryObject>) {
        Object.forEachIndexed { index, it ->
            println("${index + 1} ${it.getInfoMini()}")
        }
        println("Выберите объект:")
        val choise = Object.getOrNull(readLine()?.toIntOrNull()?.minus(1) ?: -1)
        if (choise==null) {
            println("Попробуйте заново")
            return
        }
        while (true) {
            println("Выберите действие:")
            println("1. Взять домой")
            println("2. Читать в читальном зале")
            println("3. Показать более подробную информацию.")
            println("4. Вернуть")
            println("5. Назад к выбору типа объекта")
            when (readlnOrNull()?.toIntOrNull()) {
                1 -> takeHome(choise)
                2 -> readingHere(choise)
                3 -> println(choise.getInfoGlobal())
                4 -> returnObject(choise)
                5 -> return
                else -> println("Неверный ввод!")
            }
        }
    }
}

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
            1 -> library.managementObject(books)
            2 -> library.managementObject(newspapers)
            3 -> library.managementObject(disks)
            else -> println("Неверный ввод, повторите попытку")
        }
    }
}


