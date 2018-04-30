package homework01

import java.util.Collections.max

const val MAX = 1_000_000

fun binarySearch(array: List<Int>, f: Int): Int {
    if (array.last() < f) {
        return array.size
    }
    var fromIndex = 0
    var toIndex = array.size - 1
    var index = 0

    while (fromIndex < toIndex){
        index = (toIndex + fromIndex) / 2

        if (array[index] == f)
            return index
        else
            if (array[index] < f)
                fromIndex = index + 1
            else
                toIndex = index
    }

    return toIndex
}

fun howManyNumbers(array: List<Int>, l: Int, r: Int): Int{
    val sortedArray = array.sorted()
    var fromIndex = 0
    var toIndex = array.size

    if(l >= sortedArray[0])
        fromIndex = binarySearch(sortedArray, l)
    if(r + 1 <= sortedArray.last())
        toIndex = binarySearch(sortedArray, r + 1)

    return maxOf(toIndex - fromIndex, 0)
}

fun getSumOfPrime(k: Int): Long{
    val arrayOfInclusion: Array<Boolean> = Array(MAX + 1, {false})
    var sum = 0L
    var counterOfPrimes = 0

    for(item in 2 until MAX){
        if(counterOfPrimes == k)
            return sum

        if(!arrayOfInclusion[item]){
            arrayOfInclusion[item] = true;
            sum += item
            counterOfPrimes++

            for (i in item * item until MAX step item) {
                arrayOfInclusion[i] = true
            }
        }

    }

    return sum
}

fun countTriples(a: List<Int>, b: List<Int>, c: List<Int>, x: Int): Int = 0

fun findUnique(array: List<Int>): Int = array.fold(0, {x, y -> x xor  y})

fun ternarySearch(array: List<Int>): Int{
    var fromIndex = 0
    var toIndex = array.size
    var index1 = 0
    var index2 = 0

    for(i in 1..1000){
        index1 = (fromIndex + (toIndex - fromIndex) / 3)
        index2 = (fromIndex + 2 * (toIndex - fromIndex) / 3)

        if (array[index1] < array[index2])
            fromIndex = index1
        else toIndex = index2
    }

    return maxOf(array[index1], array[index2], array[(index1 + index2) / 2])
}