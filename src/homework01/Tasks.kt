package homework01

fun binarySearch(array: List<Int>, f: Int): Int? {

    var i = 0
    val map = array.associate { it to i++ }.toSortedMap()
    return map[map.keys.toList()[binarySearchInSortedArray(array.sortedBy { it }, f)!!]]
}

fun binarySearchInSortedArray(sortedArray: List<Int>, f: Int): Int? {
    var l = 0
    var r = sortedArray.size - 1
    var mid = l + (r - l) / 2

    while (l < r) {
        when (f.compareTo(sortedArray[mid])) {
            0 -> return mid
            -1 -> r = mid - 1
            1 -> l = mid + 1
        }
        mid = l + (r - l) / 2
    }
    return if (sortedArray[mid] == f) mid else -mid
}

fun howManyNumbers(array: List<Int>, l: Int, r: Int): Int {
    val binLeft = binarySearchInSortedArray(array.sortedBy { it }, l)
    val binRight = binarySearchInSortedArray(array.sortedBy { it }, r)
    val left = if (binLeft!! < 0) -binLeft else binLeft
    val right = if (binRight!! < 0) -binRight else binRight

    return right - left + 1
}

fun getSumOfPrime(k: Int) = getSieve(k).sum()

fun getSieve(k: Int): List<Int> {
    val LIMIT = 100_000
    val size = if (k > LIMIT) LIMIT else k

    val primes: MutableCollection<Int> = mutableListOf()
    val checked = BooleanArray(size)

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