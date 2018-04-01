package homework01

const val START_LINEAR = 5

/**
 * @see binarySearchRecursion
 */
fun linearSearch(array: List<Int>, element: Int, start: Int, end: Int, compare: (Int, Int) -> Int): Int {
    for (i in start until end) {
        if (compare(array[i], element) == 0) {
            return i
        }
        if (compare(array[i], element) > 0) {
            return i.inv()
        }
    }
    return end.inv()
}

/**
 * Performs search in [start; end)
 *
 * @param compare thr comparator to be used.
 *        Should return negative value if first argument is smaller,
 *        positive value if first argument is greater
 *        and 0 if they are equal
 *
 * @see binarySearch
 */
private tailrec fun binarySearchRecursion(array: List<Int>, element: Int, start: Int, end: Int, compare: (Int, Int) -> Int): Int {
    assert(start < end)
    assert(start >= 0)
    assert(end <= array.size)

    if (end - start < START_LINEAR) {
        return linearSearch(array, element, start, end, compare)
    }

    val center = start + (end - start).shr(1)

    return if (compare(element, array[center]) <= 0) {
        binarySearchRecursion(array, element, start, center + 1, compare)
    } else {
        binarySearchRecursion(array, element, center + 1, end, compare)
    }
}

/**
 * Array is assumed to be sorted in ascending order
 * Don't want to check that, it'd spoil asymptotic complexity
 *
 * @returns index of first occurrence of element in array, if any
 *          otherwise inversion index of where it would be inserted to keep the array sorted
 *          (might therefore return array.size.inv())
 */
fun binarySearch(array: List<Int>, f: Int): Int =
        binarySearchRecursion(array, f, 0, array.size, { first: Int, second: Int -> first.compareTo(second) })

fun howManyNumbers(array: List<Int>, l: Int, r: Int): Int {
    assert(l <= r)
    val sorted = array.sorted()
    // [start; end)
    val start = binarySearch(sorted, l)
    val end = binarySearch(sorted, r + 1)
    return (if (end >= 0) end else end.inv()) - if (start >= 0) start else start.inv()
}

fun getSumOfPrime(k: Int): Long {
    val limit = 100000
    val used = BooleanArray(limit)
    var sum = 0L
    var primesFound = 0

    for (i in 2 until limit) {
        if (!used[i]) {
            sum += i
            primesFound++
            if (primesFound == k) {
                return sum
            }

            for (j in i * i until limit step i) {
                used[j] = true
            }
        }
    }
    return -1
}

fun countTriples(a: List<Int>, b: List<Int>, c: List<Int>, x: Int): Int {

    val cMap = HashMap<Int, Int>()

    for (cElement in c) {
        cMap[cElement] = (cMap[cElement] ?: 0) + 1
    }

    var sum = 0

    for (aElement in a) {
        for (bElement in b) {
            sum += cMap[x - aElement - bElement] ?: 0
        }
    }

    return sum
}

fun findUnique(array: List<Int>): Int =
        array.fold(0, { a: Int, b: Int -> a xor b })

fun linearSearchMax(array: List<Int>, start: Int, end: Int): Int {
    var indexOfMax = start
    for (i in start + 1..end) {
        if (array[i] > array[indexOfMax]) {
            indexOfMax = i
        } else {
            // Since we know values will descend further
            return indexOfMax
        }
    }
    return indexOfMax
}

/**
 * Performs search in [start; end]
 */
private tailrec fun ternarySearchRecursion(array: List<Int>, start: Int, end: Int): Int {
    if (end - start < START_LINEAR) {
        return linearSearchMax(array, start, end)
    }

    val m1 = start + (end - start) / 3
    val m2 = start + 2 * (end - start) / 3

    return when {
        array[m1] > array[m2] -> ternarySearchRecursion(array, start, m2)
        array[m1] < array[m2] -> ternarySearchRecursion(array, m1, end)
        else -> ternarySearchRecursion(array, m1, m2)
    }
}

fun ternarySearch(array: List<Int>): Int =
        ternarySearchRecursion(array, 0, array.size - 1)
