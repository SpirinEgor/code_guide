package homework01

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

    while (l <= r) {
        when (f.compareTo(array[mid])) {
            0 -> return mid
            -1 -> r = mid - 1
            1 -> l = mid + 1
        }
        mid = l + (r - l) / 2
    }

    if (mid >= array.size) return -mid - 1
    return if (array[mid] == f) mid else -mid - 1
}

fun howManyNumbers(array: List<Int>, l: Int, r: Int): Int {
    val arraySorted = array.sortedBy { it }
    val binLeft = binarySearch(arraySorted, l)
    val binRight = binarySearch(arraySorted, r)
    val left = if (binLeft < 0) -binLeft - 1 else binLeft
    val right = if (binRight < 0) -binRight - 2 else binRight

    return right - left + 1
}

fun getSumOfPrime(k: Int) = getSieve(k).sum()

fun getSieve(k: Int, limit: Int = 100_000): List<Int> {
    val primes: MutableCollection<Int> = mutableListOf()
    val checked = Array(limit, { false })

    for (i in 2L until limit) {
        if (!checked[i.toInt()]) {
            if (primes.size == k) {
                return primes.toList()
            }
            primes.add(i.toInt())
        }

        for (j in i * i until limit step i) {
            if (!checked[j.toInt()]) {
                checked[j.toInt()] = true
            }
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
            0 -> if (m1 == m2) {
                val max = array[m1]
                val mr = m1 + 1
                if (max < array[mr]) {
                    return array[mr]
                }
                val ml = m1 - 1
                if (max < array[ml]) {
                    return array[ml]
                }
                return max
            } else {
                l = m1
                r = m2
            }
            -1 -> l = m1 + 1
            1 -> r = m2 - 1
        }

        m1 = l + (r - l) / 3
        m2 = l + 2 * (r - l) / 3
    }

    return array[l]
}