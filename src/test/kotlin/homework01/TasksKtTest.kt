package homework01

import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet
import kotlin.test.assertEquals

class TasksKtTest {

    private val random = Random()

    private fun ClosedRange<Int>.random(): Int = Random().nextInt(endInclusive - start) + start

    @Test
    fun binarySearchInOneElementArray() {
        val listOfOneElement = listOf(5)
        assertEquals(0, binarySearch(listOfOneElement, 5))
    }

    @Test
    fun binarySearchInTwoElementArray() {
        val listOfTwoElements = listOf(-1, 1)

        val result1 = listOfTwoElements.binarySearch(-1)
        val result2 = listOfTwoElements.binarySearch(1)
        val result3 = listOfTwoElements.binarySearch(2)

        assertEquals(result1, binarySearch(listOfTwoElements, -1))
        assertEquals(result2, binarySearch(listOfTwoElements, 1))
        assertEquals(result3, binarySearch(listOfTwoElements, 2))
    }

    @Test
    fun binarySearchIfNotContainingElement() {
        val listOfElements = listOf(1, 5, 6, 9, 11)
        val search = listOf(0, 7, 12)

        val result1 = listOfElements.binarySearch(search[0])
        val result2 = listOfElements.binarySearch(search[1])
        val result3 = listOfElements.binarySearch(search[2])

        assertEquals(result1, binarySearch(listOfElements, search[0]))
        assertEquals(result2, binarySearch(listOfElements, search[1]))
        assertEquals(result3, binarySearch(listOfElements, search[2]))
    }

    @Test
    fun binarySearchInBigArray() {
        val listOfManyElements = ArrayList<Int>()
        val values = HashSet<Int>()
        for (i in 0..1_000_000) {
            val elem = random.nextInt()
            values.add(elem)
            listOfManyElements.add(elem)
        }
        val search = values.toList()[(0..values.size).random()]

        listOfManyElements.sortBy { it }

        /**
         * Searches the random element
         */
        assertEquals(listOfManyElements.binarySearch(search), binarySearch(listOfManyElements, search))

        /**
         * Searches the first element
         */
        assertEquals(listOfManyElements.binarySearch(listOfManyElements[0]),
                binarySearch(listOfManyElements, listOfManyElements[0]))

        /**
         * Searches the last element
         */
        assertEquals(listOfManyElements.binarySearch(listOfManyElements[listOfManyElements.size - 1]),
                binarySearch(listOfManyElements, listOfManyElements[listOfManyElements.size - 1]))
    }

    @Test
    fun howManyNumbersBothContains() {
        val list = (1..100).map { it }
        val leftBound = 1
        val rightBound = 100

        val result = list.filter { it in (leftBound)..(rightBound) }
        assertEquals(result.size, howManyNumbers(list, leftBound, rightBound))
    }

    @Test
    fun howManyNumbersOneContains() {
        val list = (0..10_000).map { 2 * it + 1 }

        var i = 1
        while (i <= list[list.size - 1]) {
            val j = i + 3
            val result = list.filter { it in (i)..(j) }
            assertEquals(result.size, howManyNumbers(list, i, j))
            i += 2
        }

        var j = 1
        while (j <= list[list.size - 1] + 2) {
            i = j - 5
            val result = list.filter { it in (i)..(j) }
            assertEquals(result.size, howManyNumbers(list, i, j))
            j += 2
        }
    }

    @Test
    fun howManyNumbersNoneContains() {
        val list = (0..1000).map { 2 * it + 1 }

        var i = 750
        var j = 1420

        while (i > 0 || j < list[list.size - 1]) {
            val result = list.filter { it in (i)..(j) }
            assertEquals(result.size, howManyNumbers(list, i, j))
            i -= 4
            j += 2
        }
    }

    @Test
    fun getSumOfPrime() {
        val k = 1000
        var res = 0
        for (i in 2..k) {
            if (isPrime(i)) {
                res += i
            }
        }
        val sum = homework01.getSumOfPrime(k)
        assertEquals(res, sum)
    }

    @Test
    fun getSieve() {
        val sieve = getSieve(1000)

        for (i in sieve) {
            assertTrue(isPrime(i))
        }
    }

    private fun isPrime(n: Int): Boolean {
        if (n == 2) return true

        if (n % 2 == 0) return false

        var j = 3
        while (j * j <= n) {
            if (n % j == 0) return false
            j += 2
        }
        return true
    }

    @Test
    fun countTriples() {
        val a = (1..1000).map { (-500..500).random() }
        val b = (1..1000).map { (-500..500).random() }
        val c = (1..1000).map { (-500..500).random() }

        val x = (-1500..1500).random()

        var count = 0
        for (i in a) {
            for (j in b) {
                for (k in c) {
                    if (i + j + k == x) {
                        count++
                    }
                }
            }
        }

        assertEquals(count, countTriples(a, b, c, x))
    }

    @Test
    fun findUniqueOfOneElement() {
        val array = listOf(5)
        val result = 5
        assertEquals(result, findUnique(array))
    }

    @Test
    fun findUniqueOfManyElements() {
        val array = listOf(2, 5, 4, 7, 5, 4, 7)
        val result = 2
        assertEquals(result, findUnique(array))
    }

    @Test
    fun ternarySearch() {
        val arrayIncreasing = Array(10, { (-100..100).random() }).toList().sortedBy { it }
        val arrayDecreasing = Array(10, { (-100..100).random() }).toList().sortedByDescending { it }

        val array = arrayIncreasing + arrayDecreasing
        val result = findMax(array)

        assertEquals(result, ternarySearch(array))
    }

    @Test
    fun ternarySearchInEqualArray() {
        val array = Array(20, { 20 }).toList()

        val result = 20
        assertEquals(result, ternarySearch(array))
    }

    @Test
    fun ternarySearchInOneElementArray() {
        val array = listOf(20)
        val result = 20
        assertEquals(result, ternarySearch(array))
    }

    @Test
    fun ternarySearchInTwoElementArray() {
        val array = listOf(40, 30)
        val result = findMax(array)
        assertEquals(result, ternarySearch(array))
    }

    @Test
    fun ternarySearchInThreeElementArray() {
        var array = listOf(60, 50, 40)
        var result = findMax(array)
        assertEquals(result, ternarySearch(array))

        array = listOf(70, 80, 75)
        result = findMax(array)
        assertEquals(result, ternarySearch(array))
    }

    private fun findMax(array: List<Int>): Int {
        var max = Int.MIN_VALUE
        for (i in array.indices) {
            if (array[i] > max) {
                max = array[i]
            }
        }
        return max
    }
}