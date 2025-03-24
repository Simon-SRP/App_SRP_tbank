package com.example

abstract class LibraryObject(val name: String, var available: Boolean, val id: Int) {
    abstract fun getInfoGlobal(): String
    abstract fun getType(): String


    fun getInfoMini() = "$name доступна: ${if (available) "Да" else "Нет"}"

    fun markAsTaken() {
        available = false
    }

    fun markAsReturned() {
        available = true
    }
}

class LibraryArhiv(){
    val books = mutableListOf(
        Book("Белые ночи", true, 12312, 320, "Достоевский Ф.М."),
        Book("Крестный отец", true, 11233, 304, "Марио Пьюзо"),
        Book("Посторонний", true, 12612, 128, "Альбер Камю")
    )
    val newspapers = mutableListOf(
        Newspaper("Инженер", true, 24535, 626,1),
        Newspaper("Бауманские сплетни", true, 24512, 12, 3),
        Newspaper("Детство", true, 24131, 481,4)
    )
    val disks = mutableListOf(
        Disk("Мстители", true, 35625, "DVD"),
        Disk("Король Лев", true, 36789, "DVD"),
        Disk("Queen. Лучшее", true, 34123, "CD")
    )
}

interface Shop<T: LibraryObject>{
    fun sell(): T
    fun showAllProducts()
}

interface HomeTakeable {
    fun takeHome()
}

interface LibraryReadable {
    fun readHere()
}
