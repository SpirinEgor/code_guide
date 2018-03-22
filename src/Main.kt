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
    val funcName = readLine()!!
    for (func in functions) {
        if (func?.name == funcName) {
            var params: Map<KParameter, Any?> = mapOf()
            for (type in func.parameters) {
                when {
                    type.type.toString() == "kotlin.collections.List<kotlin.Int>" -> {
                        try {
                            val len = readLine()!!.toInt()
                            val list = readLine()!!.split(' ').map { it.toInt() }
                            if (list.size != len) {
                                print("Incorrect list")
                                return
                            }
                            params = params.plus(Pair(type, list))
                        } catch (e: Exception) {
                            println(e.message)
                            return
                        }
                    }
                    type.type.toString() == "kotlin.Int" -> {
                        try {
                            val int = readLine()!!.toInt()
                            params = params.plus(Pair(type, int))
                        } catch (e: Exception) {
                            println(e.message)
                            return
                        }
                    }
                    else -> {
                        println(type.name)
                        return
                    }
                }
            }
            println(func.callBy(params))
            return
        }
    }
    println("No such function")
}