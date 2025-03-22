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
