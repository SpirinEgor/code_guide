package lecture08

import java.util.*

//This is Java-like implementation of Observer
//Also it's possible for objects to receive the info
//when and which they need

interface FoodListener {
    fun onFoodReady(meal: String)
}

class FamilyMember(val name: String): FoodListener {
    override fun onFoodReady(meal: String) {
        Thread.sleep(Random().nextInt(2000).toLong())
        println("$name: Yeah, I'll be there in a minute")
    }
}

class Dog: FoodListener {
    override fun onFoodReady(meal: String) {
        Thread.sleep(3000L)
        println("Dog: Waf-waf! *Runs to the kitchen*")
    }
}

class CookA {
    val listeners = mutableListOf<FoodListener>()

    fun notifyAll(meal: String) {
        listeners.forEach { it.onFoodReady(meal) }
    }

    fun cookMeal(meal: String) {
        println("The Cook has prepared $meal. Sending notifications")
        notifyAll(meal)
    }
}

fun main(args: Array<String>) {
    val gordonRamsay = CookA()
    val family = listOf(FamilyMember("John"),
                        FamilyMember("Nina"), Dog())
    gordonRamsay.listeners.addAll(family)

    gordonRamsay.cookMeal("Roast chicken")
}
