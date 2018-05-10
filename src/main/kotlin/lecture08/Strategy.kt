package lecture08


//In Kotlin strategy is just a lambda function
abstract class Warrior(val name: String = "Unknown warrior",
                       val fightStrategy: (Warrior) -> String) {

    fun attack(enemy: Warrior) {
        println("$name: " + fightStrategy(enemy))
    }

    override fun toString(): String = name
}

class Gladiator: Warrior("Gladiator", {"Smashes $it's head cruelly"})
class Centaur: Warrior("Centaur", {"Kicks $it with it's hooves"})
class Clone(warrior: Warrior): Warrior("$warrior's clone", warrior.fightStrategy)

fun main(args: Array<String>) {
    val warrior1: Warrior = Gladiator()
    val warrior2: Warrior = Centaur()

    warrior1.attack(warrior2)
    warrior2.attack(warrior1)

    var winner = warrior2
    println("Winner is $winner")

    val warrior3: Warrior = Clone(winner)
    warrior3.attack(winner)
    winner.attack(warrior3)

    winner = warrior3
    println("Winner is $winner")
}