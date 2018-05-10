package lecture08

object BeverageFactory {
    fun createBeverage(description: String): Beverage? {
        var beverage: Beverage? = null
        description.split(" + ").forEach {
            when(it) {
                "Espresso" -> beverage = Espresso()
                "Latte" -> beverage = Latte()
                "milk" -> beverage = beverage?.withMilk()
                "cinnamon" -> beverage = beverage?.withCinnamon()
            }
        }
        return beverage
    }
}

fun main(args: Array<String>) {
    val beverage = BeverageFactory.createBeverage("Espresso + milk")
    if (beverage != null)
        println("${beverage.description} costs ${beverage.cost()}")
}