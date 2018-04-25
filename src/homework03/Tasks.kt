package homework03

import java.lang.Math.abs


fun minimalDiffDivide(array: List<Int>): Int {
    var sumOfArrayNumbers: Int = 0
    for (i in 0..array.size - 1) {
        sumOfArrayNumbers += array[i]
    }
    var leftPartOfArray: Int = array[0]
    var rightPartOfArray: Int = sumOfArrayNumbers - leftPartOfArray
    var index: Int = 1;
    var minimum: Int = abs(rightPartOfArray - leftPartOfArray)
    while (index < array.size) {
        leftPartOfArray += array[index]
        rightPartOfArray -= array[index]
        if (minimum > abs(leftPartOfArray - rightPartOfArray)) {
            minimum = abs(leftPartOfArray - rightPartOfArray)
        }
        index++
    }
    return minimum
}

fun minimalScalarProduct(x: List<Int>, y: List<Int>): Int {
    var vector1 = x.toIntArray()
    var vector2 = y.toIntArray()
    vector1.sort()
    vector2.sort()
    for (i in 0..vector1.size - 1) {
        print("${vector1[i]} ")
    }
    println()
    for (i in 0..vector2.size - 1) {
        print("${vector2[i]} ")
    }
    println()
    var j: Int = vector2.size - 1;
    var minimum: Int = 0;
    for (i in 0..vector1.size - 1) {
        if (j - i >= 0) {
            println(vector1[i] * vector2[j - i]);
            minimum += (vector1[i] * vector2[j - i])
        }
    }
    return minimum
}

fun backpack(weight: List<Int>, cost: List<Int>, maxWeight: Int) {

}