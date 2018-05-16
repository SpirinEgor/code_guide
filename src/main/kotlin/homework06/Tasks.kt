package homework06

import java.util.*

abstract class Animal(private var speed: Int) {

    fun getSpeed(): Int = speed

    abstract fun makeSomeNoise()
}

interface Chased<T> {
    fun chaseBy(hunter: T)
}

interface Chasing<T> {
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
        val victimSpeed = victim.getSpeed()
        val hunterSpeed = this.getSpeed()

        if (hunterSpeed > victimSpeed) {
            println("Wolf ($hunterSpeed) caught up the victim: Deer ($victimSpeed)")
        } else {
            println("Deer ($victimSpeed) has ran away from Wolf ($hunterSpeed)")
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
        val victimSpeed = victim.getSpeed()
        val hunterSpeed = this.getSpeed()

        if (hunterSpeed > victimSpeed) {
            println("Cat ($hunterSpeed) caught up the victim: Mouse ($victimSpeed)")
        } else {
            println("Mouse ($victimSpeed) has ran away from Cat ($hunterSpeed)")
        }
    }

    override fun makeSomeNoise() {
        println("Mrrr mrrr")
    }
}

class Dog(owner: Owner, name: String?, speed: Int = Random().nextInt(20) + 15) : Pet(owner, name, speed), Chasing<Cat> {
    override fun chase(victim: Cat) {
        val victimSpeed = victim.getSpeed()
        val hunterSpeed = this.getSpeed()

        if (hunterSpeed > victimSpeed) {
            println("Dog ($hunterSpeed) caught up the victim: Cat ($victimSpeed)")
        } else {
            println("Cat ($victimSpeed) has ran away from Dog ($hunterSpeed)")
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

class Human(val name: String, speed: Int = Random().nextInt(4) + 3) : Owner, Animal(speed) {

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

    var isVisible = false

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

    val chezhCat = CheshireCat(name = null)

    chezhCat.appear()
    chezhCat.grin()
    chezhCat.disappear()

    john.getPets().forEach { john.playWith(it) }

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
