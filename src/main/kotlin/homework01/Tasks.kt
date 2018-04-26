package homework01

import kotlin.math.max

const val MAX_N = 1_000_000
const val MAX_ITER = 1000

/*
return first element, which >= f
in case all elements smaller, than f
return array.size
(it's works like lower_bound)
 */
fun binarySearch(array: List<Int>, f: Int): Int {
    if (array.last() < f) {
        return array.size
    }
    var l = 0
    var r = array.size - 1
    while (l != r) {
        val m = l + (r - l).shr(1)
        when {
            array[m] == f -> return m
            array[m] < f -> l = m + 1
            else -> r = m
        }
    }
    return l
}

fun howManyNumbers(array: List<Int>, l: Int, r: Int): Int {
    val sortedArray = array.sorted()
    val lBorder = if (l >= sortedArray[0]) binarySearch(sortedArray, l) else 0
    val rBorder = if (r + 1 <= sortedArray.last())
                            binarySearch(sortedArray, r + 1) else sortedArray.size
    return max(rBorder - lBorder, 0)
}

fun getSumOfPrime(k: Int): Long {
    val prime: MutableList<Int> = mutableListOf()
    val used: Array<Boolean> = Array(MAX_N + 1, {false})
    for (i in (2..MAX_N)) {
        if (prime.size == k) {
            return prime.fold(0.toLong(), {x, y -> x + y})
        }
        if (!used[i]) {
            prime.add(i)
            if (i.toLong() * i < MAX_N) {
                for (j in (i * i)..MAX_N step i) {
                    used[j] = true
                }
            }
        }
    }
    return -1
}

fun countTriples(a: List<Int>, b: List<Int>, c: List<Int>, x: Int): Int {
    val hashTable = HashMap<Int, Int>()
    for (elemC in c) {
        hashTable[elemC] = (hashTable[elemC] ?: 0) + 1
    }
    var result = 0
    for (elemA in a) {
        for (elemB in b) {
            result += hashTable[x - elemA - elemB] ?: 0
        }
    }
    return result
}

fun findUnique(array: List<Int>): Int = array.fold(0, {x, y -> x xor y})

fun ternarySearch(array: List<Int>): Int {
    var l = 0
    var r = array.size - 1
    for (i in 1..MAX_ITER) {
        val m1 = l + (r - l) / 3
        val m2 = l + 2 * (r - l) / 3
        when (array[m1].compareTo(array[m2])) {
            0 -> {
                l = m1
                r = m2
            }
            -1 -> {
                l = m1
            }
            else -> {
                r = m2
            }
        }
    }
    var maxVal = array[l]
    for (i in l + 1..r) {
        if (array[i] > maxVal) {
            maxVal = array[i]
        }
    }
    return maxVal
}
