package homework01

/**
 * -1 is added when value not found
 * to avoid ambiguous results when value
 * could've been inserted to 0th index
 *
 * @see binarySearchRecursion
 */
fun linearSearch(array: List<Int>, element: Int, start: Int, end: Int, compare: (Int, Int) -> Int): Int {
    for (i in start..end) {
        if (compare(array[i], element) == 0) {
            return i
        }
        if (compare(array[i], element) > 0) {
            return -i - 1
        }
    }
    return -end - 1
}

/**
 * start and end are assumed to be in [0..array.size]
 * also assumed that start <= end
 * array is assumed to be sorted in ascending order according to comparator
 *
 * @param compare thr comparator to be used.
 *        Should return negative value if first argument is smaller,
 *        positive value if first argument is greater
 *        and 0 if they are equal
 *
 * @returns index of first occurrence of element
 */
fun binarySearchRecursion(array: List<Int>, element: Int, start: Int, end: Int, compare: (Int, Int) -> Int): Int {

    // At some point recursive splitting is no longer that profitable
    if (end - start < 5) {
        return linearSearch(array, element, start, end, compare)
    }

    val center = start + (end - start) / 2

    // Case of element == array[center] is not taken into account
    // due to possibility of duplicate elements
    return if (compare(element, array[center]) <= 0) {
        binarySearchRecursion(array, element, start, center, compare)
    } else {
        binarySearchRecursion(array, element, center + 1, end, compare)
    }
}

/** Array is assumed to be sorted in ascending order
 * @returns index of first occurrence of element in array, if any
 *          otherwise (-1) * index of where it would be inserted to keep the array sorted
 */
fun binarySearch(array: List<Int>, f: Int): Int =
        binarySearchRecursion(array, f, 0, array.size - 1, { first: Int, second: Int -> first - second })

fun howManyNumbers(array: List<Int>, l: Int, r: Int): Int {
    val sorted = array.sorted()
    val reversed = sorted.reversed()

    val lFound = binarySearch(sorted, l)
    val rFound = binarySearchRecursion(reversed, r, 0, reversed.size - 1, { first: Int, second: Int -> second - first })

    // Now some weird magic here. Sorry
    val first = if (lFound >= 0) lFound else -(lFound + 1)
    val last = array.size - 1 + if (rFound >= 0) -rFound else rFound + 1

    return last - first + 1
}

/*
I'm pretty sure it'd be more efficient this way, without any explicit sieve

fun isPrime(number: Long, previousPrimes: List<Long>): Boolean = previousPrimes.all { number % it != 0L }

fun getSumOfPrime(k: Int): Long {

    val primes = ArrayList<Long>()

    var currentNumber = 2L

    while (primes.count() < k) {
        if (isPrime(currentNumber, primes)) {
            primes.add(currentNumber)
        }
        currentNumber++
    }

    // for k <= 0 primes will be empty and sum() will return 0
    return primes.sum()
}
*/

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

fun dumbCountTriples(a: List<Int>, b: List<Int>, c: List<Int>, x: Int): Int {
    var sum = 0
    for (aElement in a) {
        for (bElement in b) {
            for (cElement in c) {
                if (aElement + bElement + cElement == x) {
                    sum++
                }
            }
        }
    }
    return sum
}

// I managed to get it in O(n^2log(n))
fun countTriples(a: List<Int>, b: List<Int>, c: List<Int>, x: Int): Int {
    val cSorted = c.sorted()
    val cSortedReversed = cSorted.reversed()

    var sum = 0

    for (aElement in a) {
        for (bElement in b) {
            val target = x - aElement - bElement
            val cElementFound = binarySearch(cSorted, target)

            if (cElementFound < 0) {
                continue
            }

            val cElementEnd = c.size - 1 - binarySearchRecursion(
                    cSortedReversed,
                    target,
                    0,
                    c.size - 1,
                    { first: Int, second: Int -> second - first }
            )
            val cElements = cElementEnd - cElementFound + 1
            sum += cElements
        }
    }

    return sum
}

fun findUnique(array: List<Int>): Int {
    var result = 0
    for (element in array) {
        result = result xor element
    }
    return result
}

/**
 * @returns 1 if values are ascending
 *          -1 if values are descending
 *          0 if index is a stationary point
 */
fun getState(array: List<Int>, index: Int): Int =
        when (index) {
            0 -> when {
                array[0] < array[1] -> 1
                array[0] > array[1] -> -1
                else -> 0
            }
            array.size - 1 -> when {
                array[index - 1] < array[index] -> 1
                array[index - 1] > array[index] -> -1
                else -> 0
            }
            else -> when {
                array[index - 1] < array[index] && array[index] < array[index + 1] -> 1
                array[index - 1] > array[index] && array[index] > array[index + 1] -> -1
                else -> 0
            }
        }

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

fun ternarySearchRecursion(array: List<Int>, start: Int, end: Int): Int {
    if (end - start < 5) {
        linearSearchMax(array, start, end)
    }

    val m1 = start + (end - start) / 3
    val m2 = start + 2 * (end - start) / 3

    return when (getState(array, m1)) {
        1 -> when (getState(array, m2)) {
            1 -> ternarySearchRecursion(array, m2 + 1, end)
            -1 -> ternarySearchRecursion(array, m1 + 1, m2 - 1)
            else -> m2
        }
        -1 -> ternarySearchRecursion(array, start, m1 - 1)
        else -> m1
    }
}

fun ternarySearch(array: List<Int>): Int =
        ternarySearchRecursion(array, 0, array.size - 1)
