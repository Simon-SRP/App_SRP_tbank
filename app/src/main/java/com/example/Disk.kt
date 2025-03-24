package com.example

class Disk(name: String, available: Boolean, id: Int, val types: String):
    LibraryObject(name,available,id), HomeTakeable {

    override fun getInfoGlobal() =
        "$types $name доступен: ${if (available) "Да" else "Нет"}"

    override fun getType() = "Диск"

    override fun takeHome() {
        println("Диск $id взят домой")
    }
}

class DiskShop : Shop<Disk> {
    val disksShop = listOf(
        Disk("Человек-паук", true, 2001, "DVD"),
        Disk("Тачки", true, 2002, "DVD"),
        Disk("Сборник хитов Kiss", true, 2003, "CD")
    )
    private var currentIndex = 0

    override fun sell(): Disk {
        val disk = disksShop[currentIndex]
        currentIndex = (currentIndex + 1) % disksShop.size
        return disk
    }

    override fun showAllProducts() {
        println("Доступные диски:")
        disksShop.forEachIndexed { index, disk ->
            println("${index + 1}. ${disk.getInfoMini()}")
        }
    }
}

class DigitizationInCD {
    fun bookToCD(book: Book, library: LibraryArhiv): Disk {
        val disk = Disk("Оцифрованная книга: ${book.name}", true, book.id, "CD")
        library.disks.add(disk)
        return disk
    }

    fun newspaperToCD(newspaper: Newspaper, library: LibraryArhiv): Disk {
        val disk = Disk("Оцифрованная газета: ${newspaper.name}", true, newspaper.id, "CD")
        library.disks.add(disk)
        return disk
    }
}