package lecture08

abstract class Beverage(val description: String = "Unknown beverage") {

    abstract fun cost(): Int
}

class Espresso: Beverage("Espresso") {
    override fun cost(): Int {
        return 60
    }
}

class Latte: Beverage("Latte") {
    override fun cost(): Int {
        return 100
    }
}

abstract class Decorator(val baseBeverage: Beverage, name: String): Beverage(name)

class MilkDecorator(baseBeverage: Beverage):
        Decorator(baseBeverage, "${baseBeverage.description} + milk") {
    override fun cost(): Int {
        return baseBeverage.cost() + 20
    }
}

class CinnamonDecorator(baseBeverage: Beverage):
        Decorator(baseBeverage, "${baseBeverage.description} + cinnamon") {
    override fun cost(): Int {
        return baseBeverage.cost() + 40
    }
}

fun Beverage.withMilk(): Beverage = MilkDecorator(this)
fun Beverage.withCinnamon(): Beverage = CinnamonDecorator(this)

fun main(args: Array<String>) {
    var coffee: Beverage = Espresso()
    coffee = MilkDecorator(coffee)
    coffee = CinnamonDecorator(coffee)
    println("${coffee.description} costs ${coffee.cost()}")

    //Or, using extension methods
    coffee = Latte().withCinnamon().withMilk()
    println("${coffee.description} costs ${coffee.cost()}")
}