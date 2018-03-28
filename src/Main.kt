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
            ::findUnique.javaMethod?.kotlinFunction,
            ::ternarySearch.javaMethod?.kotlinFunction
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

fun binarySearch(array: List<Int>, f: Int): Int {
    var begin: Int = 0;
    var end: Int = array.size - 1
    var i: Int = (begin + end) / 2
    while (begin <= end && array[i] != f) {
        if (array[i] > f) {
            end = i - 1
            i = (begin + end) / 2
        } else if (array[i] < f) {
            begin = i + 1
            i = (begin + end) / 2
        }
    }
    if (array[i] != f) {
        return -1
    } else {
        return i
    }
}

fun binarySearch2(array: List<Int>, f: Int): Float {
    var begin: Int = 0;
    var end: Int = array.size - 1
    var i: Int = (begin + end) / 2
    while (begin <= end && array[i] != f) {
        if (array[i] > f) {
            end = i - 1
            i = (begin + end) / 2
        } else if (array[i] < f) {
            begin = i + 1
            i = (begin + end) / 2
        }
    }

    if (array[i] != f) {
        return (begin + end.toFloat()) / 2
    } else {
        return i.toFloat()
    }
}

fun howManyNumbers(array: List<Int>, l: Int, r: Int): Int {
    array.toIntArray().sort()
    var left: Float = binarySearch2(array, l);
    var right: Float = binarySearch2(array, r);
    if (left > left.toInt().toFloat()) {
        return right.toInt() - left.toInt()
    } else {
        return right.toInt() - left.toInt() + 1
    }
}

fun getSumOfPrime(k: Int): Long {
    val array = Array(10001, { i -> 0 })
    for (i in 2..array.size - 1 step 1) {
        if (array[i] == 0) {
            for (j in 2 * i..array.size - 1 step i) {
                array[j] = 1
            }
        }
    }
    var s: Long = 0
    var i: Int = 0
    var j: Int = 2
    while (i != k && j < array.size) {
        if (array[j] == 0) {
            println(j)
            s += j
            i++
        }
        j++
    }
    return s
}

fun countTriples(a: List<Int>, b: List<Int>, c: List<Int>, x: Int): Int {
    a.toIntArray().sort()
    b.toIntArray().sort()
    c.toIntArray().sort()
    var k: Int = 0;
    for (i in 0..a.size - 1) {
        for (j in 0..b.size - 1) {
            if (binarySearch(c, x - (a[i] + b[j])) != -1) {
                k++
            }
        }
    }
    return k;
}
