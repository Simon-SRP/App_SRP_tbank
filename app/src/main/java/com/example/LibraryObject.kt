package com.example

abstract class LibraryObject(val name: String, var available: Boolean, val id: Int) {
    abstract fun getInfoGlobal(): String
    abstract fun canBeTakenHome(): Boolean
    abstract fun canBeReadHere(): Boolean
    abstract fun getType(): String


    fun getInfoMini() = "$name доступна: ${if (available) "Да" else "Нет"}"

    fun markAsTaken() {
        available = false
    }

    fun markAsReturned() {
        available = true
    }
}