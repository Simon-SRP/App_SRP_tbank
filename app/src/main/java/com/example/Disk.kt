package com.example

class Disk(name: String, available: Boolean, id: Int, val types: String): LibraryObject(name,available,id){
    override fun getInfoGlobal() =
        "$types $name доступен: ${if (available) "Да" else "Нет"}"

    override fun getType() = "Диск"
    override fun canBeTakenHome() = true
    override fun canBeReadHere() = false
}