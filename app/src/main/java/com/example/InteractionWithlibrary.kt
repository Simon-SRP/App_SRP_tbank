package com.example

class InteractionWithLibrary {
    fun takeHome(obj: LibraryObject) {
        runCatching {
            require(obj.available) { "Объект ${obj.id} недоступен!" }
            require(obj.canBeTakenHome()) { "Этот тип объекта нельзя брать домой!" }
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
            require(obj.canBeReadHere()) { "Этот тип объекта нельзя читать в зале!" }
            obj.markAsTaken()
            println("${obj.getType()} ${obj.id} взят для чтения в зале")
        }.onFailure { println(it.message) }
    }

    fun manageObjects(objects: List<LibraryObject>) {
        objects.forEachIndexed { index, obj ->
            println("${index + 1}. ${obj.getInfoMini()}")
        }
        print("Выберите объект: ")
        val choise = objects.getOrNull(readLine()?.toIntOrNull()?.minus(1) ?: -1)
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
                2 -> readInLibrary(choise)
                3 -> println(choise.getInfoGlobal())
                4 -> returnObject(choise)
                5 -> return
                else -> println("Неверный ввод!")
            }
        }
    }
}
