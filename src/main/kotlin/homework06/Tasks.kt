package homework06

import java.util.*

abstract class Animal(private var speed: Int) {

    fun getSpeed(): Int = speed

    fun <T : Animal, H : Animal> getCaughtMessage(hunter: T, victim: H): String =
            "${hunter.javaClass.simpleName} (${hunter.speed}) caught up the victim: ${victim.javaClass.simpleName} (${victim.speed})"

    fun <T : Animal, H : Animal> ranAwayMessage(hunter: T, victim: H): String =
            "${victim.javaClass.simpleName} (${victim.speed}) has ran away from ${hunter.javaClass.simpleName} (${hunter.speed})"

    abstract fun makeSomeNoise()
}

interface Chased<in T> {
    fun chaseBy(hunter: T)
}

interface Chasing<in T> {
    fun chase(victim: T)
}

interface Owner {
    val petsOwned: MutableList<Pet>

    fun addPet(pet: Pet)
    fun getPets(): List<Pet>
    fun playWith(pet: Pet)
}

abstract class Pet(private val owner: Owner?, val name: String?, speed: Int) : Animal(speed) {
    fun getOwner() = owner
}

class Wolf(speed: Int = Random().nextInt(20) + 20) : Animal(speed), Chasing<Deer> {
    override fun chase(victim: Deer) {
        if (this.getSpeed() > victim.getSpeed()) {
            println(getCaughtMessage(this, victim))
        } else {
            println(ranAwayMessage(this, victim))
        }

    }

    override fun makeSomeNoise() {
        println("Wooooouh")
    }
}

open class Cat(owner: Owner?, name: String?, speed: Int = Random().nextInt(10) + 5) :
        Pet(owner, name, speed), Chasing<Mouse>, Chased<Dog> {

    override fun chaseBy(hunter: Dog) {
        hunter.chase(this)
    }

    override fun chase(victim: Mouse) {
        if (this.getSpeed() > victim.getSpeed()) {
            println(getCaughtMessage(this, victim))
        } else {
            println(ranAwayMessage(this, victim))
        }
    }

    override fun makeSomeNoise() {
        println("Mrrr mrrr")
    }
}

class Dog(owner: Owner, name: String?, speed: Int = Random().nextInt(20) + 15) : Pet(owner, name, speed), Chasing<Cat> {
    override fun chase(victim: Cat) {
        if (this.getSpeed() > victim.getSpeed()) {
            println(getCaughtMessage(this, victim))
        } else {
            println(ranAwayMessage(this, victim))
        }
    }

    override fun makeSomeNoise() {
        println("Waf waf")
    }
}

class Mouse(speed: Int = Random().nextInt(7) + 3) : Animal(speed), Chased<Cat> {
    override fun chaseBy(hunter: Cat) {
        hunter.chase(this)
    }

    override fun makeSomeNoise() {
        println("Pi pi pi")
    }
}

class Deer(speed: Int = Random().nextInt(25) + 10) : Animal(speed), Chased<Wolf> {
    override fun chaseBy(hunter: Wolf) {
        hunter.chase(this)
    }

    override fun makeSomeNoise() {
        println("Breeeee")
    }

}

class Human(private val name: String, speed: Int = Random().nextInt(4) + 3) : Owner, Animal(speed) {

    override fun makeSomeNoise() {
        println("Hello, my name is $name")
    }

    override val petsOwned: MutableList<Pet> = mutableListOf()

    override fun addPet(pet: Pet) {
        petsOwned.add(pet)
    }

    override fun getPets(): List<Pet> {
        return petsOwned.toList()
    }

    override fun playWith(pet: Pet) {
        print("$name playing with ${pet.name}: ")
        pet.makeSomeNoise()
    }
}

class CheshireCat(owner: Owner? = null, name: String?, speed: Int = Random().nextInt(1) + 2) : Cat(owner, name, speed) {

    override fun makeSomeNoise() {
        print("What sound a cheshire cat does????")
    }

    private var isVisible = false

    fun isCatVisible(): Boolean = isVisible

    fun grin() {
        println("Cheshire cat grins widely.")
    }

    fun appear() {
        println("Cheshire cat suddenly appears right on the tree bench.")
        isVisible = true
    }

    fun disappear() {
        println("Cheshire cat's body disappears. " +
                "It's grin is still hanging in the air. Now it's gone too.")
        isVisible = false
    }
}

fun main(args: Array<String>) {
    val john = Human("John")
    john.addPet(Cat(john, "Garfield"))
    john.addPet(Dog(john, "Odie"))

    val czechCat = CheshireCat(name = null)

    czechCat.appear()
    czechCat.grin()
    czechCat.disappear()

    john.petsOwned.forEach { john.playWith(it) }

    Wolf().chase(Deer())
    val catPet: Pet = john.getPets()[0]
    val dogPet: Pet = john.getPets()[1]
    if (catPet is Cat) {
        if (dogPet is Dog) {
            dogPet.chase(catPet)
            catPet.chaseBy(dogPet)
            catPet.chase(Mouse(39))
        }
    }
}
