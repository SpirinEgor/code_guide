package homework01

import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet
import kotlin.test.assertEquals

class TasksKtTest {

    @Test
    fun positionOfSearchingElementInOneElementArray() {
        val listOfOneElement = listOf(5)
        val result = 0
        assertEquals(result, binarySearch(listOfOneElement, 5))
    }

    @Test
    fun positionOfSearchingElementInTwoElementArray() {
        val listOfTwoElements = listOf(-1, 1)

        val result1 = 0
        val result2 = 1

        assertEquals(result1, binarySearch(listOfTwoElements, -1))
        assertEquals(result2, binarySearch(listOfTwoElements, 1))
    }

    @Test
    fun potentialPositionOfSearchingElementIfNotContainingElement() {
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
    fun positionOfSearchingElementInBigArray() {
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
        val result1 = listOfManyElements.binarySearch(search)
        assertEquals(result1, binarySearch(listOfManyElements, search))

        /**
         * Searches the first element
         */
        val result2 = listOfManyElements.binarySearch(listOfManyElements[0])
        assertEquals(result2, binarySearch(listOfManyElements, listOfManyElements[0]))

        /**
         * Searches the last element
         */
        val result3 = listOfManyElements.binarySearch(listOfManyElements[listOfManyElements.size - 1])
        assertEquals(result3, binarySearch(listOfManyElements, listOfManyElements[listOfManyElements.size - 1]))
    }

    @Test
    fun countOfNumbersInIntervalIfBothBoundsContainsInArray() {
        val list = (1..100).map { it }
        val leftBound = 1
        val rightBound = 100

        val result = list.filter { it in (leftBound)..(rightBound) }.size
        assertEquals(result, howManyNumbers(list, leftBound, rightBound))
    }

    @Test
    fun countOfNumbersInIntervalIfOneBoundContainsInArray() {
        val list = (0..10_000).map { 2 * it + 1 }

        var i = 1
        while (i <= list[list.size - 1]) {
            val j = i + 3
            val result = list.filter { it in (i)..(j) }.size
            assertEquals(result, howManyNumbers(list, i, j))
            i += 2
        }

        var j = 1
        while (j <= list[list.size - 1] + 2) {
            i = j - 5
            val result = list.filter { it in (i)..(j) }.size
            assertEquals(result, howManyNumbers(list, i, j))
            j += 2
        }
    }

    @Test
    fun countOfNumbersInIntervalIfNoneOfBoundsContainsInArray() {
        val list = (0..1000).map { 2 * it + 1 }

        var i = 750
        var j = 1420

        while (i > 0 || j < list[list.size - 1]) {

            val result = list.filter { it in (i)..(j) }.size
            assertEquals(result, howManyNumbers(list, i, j))

            i -= 4
            j += 2
        }
    }

    @Test
    fun countSumOfPrimes() {
        val limit = 100_000
        val k = 1000
        var count = 0
        var res = 0
        for (i in 2..limit) {
            if (isPrime(i)) {
                if (count == k) {
                    break
                }
                res += i
                count++
            }
        }
        val sum = homework01.getSumOfPrime(k)
        assertEquals(res, sum)
    }

    @Test
    fun countSumOfPrimesOf100_000Elements() {
        val k = 100_000
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
    fun checkIfAnyNumberInSieveIsPrimeAndNothingOtherContains() {
        val sieve = getSieve(1000)

        for (i in sieve) {
            val result = isPrime(i)
            assertTrue(result)
        }
    }

    @Test
    fun compareCountTriplesWithDumbSolution() {
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
    fun uniqueElementInOneElementArray() {
        val array = listOf(5)
        val result = 5
        assertEquals(result, findUnique(array))
    }

    @Test
    fun uniqueElementInSomeElementsArray() {
        val array = listOf(2, 5, 4, 7, 5, 4, 7)
        val result = 2
        assertEquals(result, findUnique(array))
    }

    @Test
    fun findMaxInRandomlyGenerated20SizedArray() {
        val arrayIncreasing = Array(10, { (-100..100).random() }).toList().sortedBy { it }
        val arrayDecreasing = Array(10, { (-100..100).random() }).toList().sortedByDescending { it }

        val array = arrayIncreasing + arrayDecreasing
        val result = findMax(array)

        assertEquals(result, ternarySearch(array))
    }

    @Test
    fun findMaxElementInArrayOfEqualElements() {
        val array = Array(20, { 20 }).toList()

        val result = 20
        assertEquals(result, ternarySearch(array))
    }

    @Test
    fun findMaxElementInOneElementArray() {
        val array = listOf(20)
        val result = 20
        assertEquals(result, ternarySearch(array))
    }

    @Test
    fun findMaxElementInOneTwoArray() {
        val array = listOf(40, 30)
        val result = findMax(array)
        assertEquals(result, ternarySearch(array))
    }

    @Test
    fun findMaxElementInThreeElementArray() {
        var array = listOf(60, 50, 40)
        var result = findMax(array)
        assertEquals(result, ternarySearch(array))

        array = listOf(70, 80, 75)
        result = findMax(array)
        assertEquals(result, ternarySearch(array))
    }

    @Test
    fun binarySearch() {
        val testSimple = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        assertEquals(5, binarySearch(testSimple, 6))

        val testSame = listOf(1, 2, 3, 4, 4, 4, 4, 5, 6, 7)
        val findPos = binarySearch(testSame, 4)
        assertEquals(4, testSame[findPos])

        val testLeft = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        assertEquals(0, binarySearch(testLeft, 1))

        val testRight = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        assertEquals(9, binarySearch(testRight, 10))
    }

    /**
     * Some staff functions
     */

    private fun findMax(array: List<Int>): Int {
        var max = Int.MIN_VALUE
        for (i in array.indices) {
            if (array[i] > max) {
                max = array[i]
            }
        }
        return max
    }

    private fun isPrime(n: Int): Boolean {
        if (n == 2) return true

        if (n % 2 == 0) return false

        var j = 3
        while (j * j <= n) {
            if (n % j == 0) return false
            j++
        }
        return true
    }

    private val random = Random()

    private fun ClosedRange<Int>.random(): Int = Random().nextInt(endInclusive - start) + start

}