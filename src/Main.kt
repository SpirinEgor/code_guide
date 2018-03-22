import homework01.*
import kotlin.reflect.KParameter
import kotlin.reflect.jvm.javaMethod
import kotlin.reflect.jvm.kotlinFunction

fun main(args: Array<String>) {
    val functions = listOf(
            ::howManyNumbers.javaMethod?.kotlinFunction,
            ::binarySearch.javaMethod?.kotlinFunction,
            ::getSumOfPrime.javaMethod?.kotlinFunction,
            ::countTriples.javaMethod?.kotlinFunction,
            ::findUnique.javaMethod?.kotlinFunction
    )
    val funcName = readLine()
    if (funcName == null || !functions.any{it?.name == funcName}) {
        println("No such function")
        return
    }
    val currentFunction = functions.find { it?.name == funcName }
    var params: Map<KParameter, Any?> = mapOf()
    currentFunction?.parameters?.forEach{
        when {
            it.type.toString() == "kotlin.collections.List<kotlin.Int>" -> {
                val len = getInt() ?: return
                val list = getListOfInt() ?: return
                if (list.size != len) {
                    println("Incorrect size of list")
                    return
                }
                params = params.plus(Pair(it, list))
            }
            it.type.toString() == "kotlin.Int" -> {
                val int = getInt() ?: return
                params = params.plus(Pair(it, int))
            }
            else -> {
                println("I can't do anything with ${it.name}")
                return
            }
        }
    }
    println(currentFunction?.callBy(params))
}

fun getInt(): Int? {
    return try {
        readLine()!!.toInt()
    } catch (e: Exception) {
        println(e.message)
        null
    }
}

fun getListOfInt(): List<Int>? {
    return try {
        readLine()!!.split(' ').map{it.toInt()}
    } catch (e: Exception) {
        println(e.message)
        null
    }
}
