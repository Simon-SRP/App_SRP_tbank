package com.example

class Newspaper(name: String, available: Boolean, id: Int, val numberOfSerial: Int, val month: Int):
    LibraryObject(name,available,id), LibraryReadable {

    override fun getInfoGlobal() =
        "выпуск: $numberOfSerial месяца ${nameMonth(month)} газеты $name с id:$id доступен: ${if (available) "Да" else "Нет"}"

    override fun getType() = "Газета"

    override fun readHere() {
        println("Газета $id читается в библиотеке")
    }

    fun nameMonth(month: Int): String{
        return when(month){
            1 -> "Январь"
            2 -> "Февраль"
            3 -> "Март"
            4 -> "Апрель"
            5 -> "Май"
            6 -> "Июнь"
            7 -> "Июль"
            8 -> "Август"
            9 -> "Сентябрь"
            10 -> "Октябрь"
            11 -> "Ноябрь"
            12 -> "Декабрь"
            else -> "Неверно указан месяц."
        }
    }
}

class NewspaperShop : Shop<Newspaper> {
    val newspapersShop = listOf(
        Newspaper("Космонавтика", true, 31221, 626, 5),
        Newspaper("Будни москвича", true, 32314, 12, 6),
        Newspaper("Тополь", true, 33462, 481, 7)
    )
    private var currentIndex = 0

    override fun sell(): Newspaper {
        val newspaper = newspapersShop[currentIndex]
        currentIndex = (currentIndex + 1) % newspapersShop.size
        return newspaper
    }

    override fun showAllProducts() {
        println("Доступные газеты:")
        newspapersShop.forEachIndexed { index, newspaper ->
            println("${index + 1}. ${newspaper.getInfoMini()}")
        }
    }
}