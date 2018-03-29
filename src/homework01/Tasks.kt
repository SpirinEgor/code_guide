package homework01

import java.lang.Math.min


/**
 * DEPRECATED
 *
 * Binary search in unsorted array
 * Return the index of the found element in the original array
 * If element hasn't found, return -position
 */

fun binarySearchInUnsortedArray(array: List<Int>, f: Int): Int {
    var i = 0
    val map = array.associate { it to i++ }.toSortedMap()

    val position = binarySearch(array.sortedBy { it }, f)
    if (position < 0)
        return position

    return map[map.keys.toList()[position]]!!
}

fun binarySearch(array: List<Int>, f: Int): Int {
    var l = 0
    var r = array.size - 1
    var mid = l + (r - l) / 2

    while (l < r) {
        when (f.compareTo(array[mid])) {
            0 -> return mid
            -1 -> r = mid - 1
            1 -> l = mid + 1
        }
        mid = l + (r - l) / 2
    }
    return if (array[mid] == f) mid else -mid
}

fun howManyNumbers(array: List<Int>, l: Int, r: Int): Int {
    val binLeft = binarySearch(array, l)
    val binRight = binarySearch(array, r)
    val left = if (binLeft < 0) -binLeft else binLeft
    val right = if (binRight < 0) -binRight else binRight

    return right - left + 1
}

fun getSumOfPrime(k: Int) = getSieve(k, limit = 100_000).sum()

fun getSieve(k: Int, limit: Int): List<Int> {
    val size = min(k, limit)

    val primes: MutableCollection<Int> = mutableListOf()
    val checked = Array(size, {false} )

    for (i in 2 until size) {
        if (!checked[i]) {
            primes.add(i)
        }

        var j = i * i
        while (j < size) {
            if (!checked[j]) {
                checked[j] = true
            }
            j += i
        }
    }
    return primes.toList()
}

fun countTriples(a: List<Int>, b: List<Int>, c: List<Int>, x: Int): Int {
    val elementsOfC = HashMap<Int, Int>()
    c.forEach { elementsOfC[it] = (elementsOfC[it] ?: 0) + 1 }

    var s = 0
    for (_a in a) {
        for (_b in b) {
            s += elementsOfC[x - _a - _b] ?: 0
        }
    }
    return s
}

fun findUnique(array: List<Int>): Int = array.fold(0, { res, element -> element xor res })

fun ternarySearch(array: List<Int>): Int {
    var l = 0
    var r = array.size - 1

    var m1 = l + (r - l) / 3
    var m2 = l + 2 * (r - l) / 3

    while (l < r) {
        when (array[m1].compareTo(array[m2])) {
            0 -> if (m1 == m2)
                return array[m1]
            else {
                l = m1
                r = m2
            }
            -1 -> l = m1 + 1
            1 -> r = m2 - 1
        }

        m1 = l + (r - l) / 3
        m2 = l + 2 * (r - l) / 3 + 1
    }
    return array[l]
}