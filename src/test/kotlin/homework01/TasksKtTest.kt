package homework01

import org.junit.Test

import org.junit.Assert.*
import java.lang.Math.min
import java.lang.Math.max
import java.lang.Math.abs
import java.util.*

const val SEED = 62352L
const val ARRAY_SIZE = 10
const val BOUND = 1000

class TasksKtTest {

    @Test
    fun testBinarySearchOnPresentValues() {
        val random = Random(SEED)
        val array = (1..ARRAY_SIZE).map { random.nextInt(BOUND) }.sorted()

        val indices = array.map { binarySearch(array, it) }
        val correctIndices = array.map { array.binarySearch(it) }

        assertArrayEquals(correctIndices.toIntArray(), indices.toIntArray())
    }

    @Test
    fun testBinarySearchOnNotPresentValues() {
        val random = Random(SEED)
        val array = (1..ARRAY_SIZE).map { random.nextInt(BOUND) }.sorted()
        val values = (1..ARRAY_SIZE).map { random.nextInt(BOUND) }.filter { !array.contains(it) }

        val indices = values.map { binarySearch(array, it) }
        val correctIndices = values.map { array.binarySearch(it) }

        assertArrayEquals(correctIndices.toIntArray(), indices.toIntArray())
    }

    private fun dumbHowManyNumbers(array: List<Int>, l: Int, r: Int): Int {
        var sum = 0
        for (element in array) {
            if (element in (l..r)) {
                sum++
            }
        }
        return sum
    }

    @Test
    fun testHowManyNumbersOnRandomArray() {
        val random = Random(SEED)
        val array = (1..ARRAY_SIZE).map { random.nextInt(BOUND) }
        val first = random.nextInt(BOUND)
        val second = random.nextInt(BOUND)
        val l = min(first, second)
        val r = max(first, second)

        val result = howManyNumbers(array, l, r)
        val dumbResult = dumbHowManyNumbers(array, l, r)

        assertEquals(result, dumbResult)
    }

    @Test
    fun testGetSumOfPrimeOnSmallValue() {
        val k = 6

        val result = getSumOfPrime(k)
        val dumbResult = dumbGetSumOfPrime(k)

        assertEquals(result, dumbResult)
    }

    @Test
    fun testGetSumOfPrimeOnZeroValue() {
        val k = 0

        val result = getSumOfPrime(k)
        val dumbResult = dumbGetSumOfPrime(k)

        assertEquals(result, dumbResult)
    }

    @Test
    fun testGetSumOfPrimesOnLargeValue() {
        val k = 37

        val result = getSumOfPrime(k)
        val dumbResult = dumbGetSumOfPrime(k)

        assertEquals(result, dumbResult)
    }

    private fun dumbCountTriples(a: List<Int>, b: List<Int>, c: List<Int>, x: Int): Int {
        var sum = 0
        for (i in a) {
            for (j in b) {
                for (k in c) {
                    if (i + j + k == x) {
                        sum++
                    }
                }
            }
        }
        return sum
    }

    @Test
    fun testCountTriples() {
        val random = Random(SEED)
        val a = (1..ARRAY_SIZE).map { random.nextInt(BOUND) }
        val b = (1..ARRAY_SIZE).map { random.nextInt(BOUND) }
        val c = (1..ARRAY_SIZE).map { random.nextInt(BOUND) }
        val x = abs(random.nextInt(BOUND))

        val result = countTriples(a, b, c, x)
        val dumbResult = dumbCountTriples(a, b, c, x)

        assertEquals(result, dumbResult)
    }

    private fun dumbFindUnique(array: List<Int>): Int {
        for (i in (0 until array.size)) {
            if (((0 until array.size) - i).all { array[it] != array[i] }) {
                return array[i]
            }
        }
        throw Exception("Array is incorrect")
    }

    @Test
    fun testFindUnique() {
        val random = Random(SEED)
        val first = (1..ARRAY_SIZE).shuffled(random).subList(0, ARRAY_SIZE - 1)
        val second = (1..ARRAY_SIZE).shuffled(random)
        val array = (first + second).shuffled(random)

        val result = findUnique(array)
        val dumbResult = dumbFindUnique(array)

        assertEquals(result, dumbResult)
    }

    @Test
    fun testTernarySearchOnDescendingArray() {
        val random = Random(SEED)
        val ascending = (1..ARRAY_SIZE)
                .map { random.nextInt(BOUND) }
                .distinct()
                .sorted()
                .reversed()

        val result = ternarySearch(ascending)

        assertEquals(ascending[0], result)
    }

    @Test
    fun testTernarySearchOnAscendingArray() {
        val random = Random(SEED)
        val ascending = (1..ARRAY_SIZE)
                .map { random.nextInt(BOUND) }
                .distinct()
                .sorted()

        val result = ternarySearch(ascending)

        assertEquals(ascending[ascending.size - 1], result)
    }

    @Test
    fun testTernarySearchOnRandomArray() {
        val random = Random(SEED)

        // Actual numbers might be slightly smaller due to call of .distinct()
        val numberOfAscending = random.nextInt(ARRAY_SIZE)
        val numberOfDescending = ARRAY_SIZE - numberOfAscending
        val ascending = (1..numberOfAscending)
                .map { random.nextInt(BOUND) }
                .distinct()
                .sorted()
        val descending = (1..numberOfDescending)
                .map { random.nextInt(BOUND) }
                .filter { it < ascending[ascending.size - 1] }
                .distinct()
                .sorted()
                .reversed()
        val array = ascending + descending

        val result = ternarySearch(array)
        val correctResult = array.max()

        assertEquals(correctResult, result)
    }
}