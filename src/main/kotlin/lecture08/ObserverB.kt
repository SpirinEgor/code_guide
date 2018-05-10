package lecture08

import kotlin.properties.Delegates

//Implementation of Observer using standard
//Observer in Kotlin Delegates
class CookB {
    val listeners = mutableListOf<FoodListener>()

    var meal: String by Delegates.observable("") {
        prop, old, new -> listeners.forEach { it.onFoodReady(new) }
    }

    fun cookMeal(meal: String) {
        println("The Cook has prepared $meal. Sending notifications")
        this.meal = meal
    }
}

fun main(args: Array<String>) {
    val gordonRamsay = CookB()
    val family = listOf(FamilyMember("John"),
                        FamilyMember("Nina"), Dog())
    gordonRamsay.listeners.addAll(family)

    gordonRamsay.cookMeal("Roast chicken")
}
