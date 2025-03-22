package com.example

class Newspaper(name: String, available: Boolean, id: Int, val numberOfSerial: Int):
    LibraryObject(name,available,id), LibraryReadable {

    override fun getInfoGlobal() =
        "выпуск: $numberOfSerial газеты $name с id:$id доступен: ${if (available) "Да" else "Нет"}"

    override fun getType() = "Газета"

    override fun readHere() {
        println("Газета $id читается в библиотеке")
    }

}