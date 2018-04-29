package homework06


open class Animal

interface Owner {
    //TODO: Правильные аргументы и возвращаемые значения
    fun addPet()
    fun getPets()
    fun playWith()
}

//TODO: Pet - интерфейс или класс?

class Wolf

//TODO: дописать все эти классы
class Cat {
}

class Dog {
}

class Human {
}

class CheshireCat {
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
    //TODO: Есть человек John, у него есть кот Garfield и
    //TODO: пес Odie - питомцы.

    //TODO: Также есть чеширский кот без владельца (owner = null).
    //TODO: Он появляется, улыбается и исчезает.

    //TODO: После этого John играет со своими питомцами по очереди.
}
