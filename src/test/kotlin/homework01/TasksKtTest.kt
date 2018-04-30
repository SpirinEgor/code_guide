package homework01

import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Test
import java.util.*
import kotlin.test.assertNotEquals

class TasksKtTest {

    //binarySearch
    @Test
    fun binarySearchWrongIndex() {
        val array = listOf(0, 8, 22, 37, 46, 58, 215)

        assertEquals(2, binarySearch(array, 22))
        assertNotEquals(5, binarySearch(array, 22))
    }

    @Test
    fun binarySearchBound() {
        val array = listOf(0, 8, 22, 37, 46, 58, 215)

        assertEquals(0, binarySearch(array, 0))
        assertEquals(array.size - 1, binarySearch(array, 215))
    }

    @Test
    fun binarySearchOutOfBound(){
        val array = listOf(0, 8, 22, 37, 46, 58, 215)

        assertEquals(array.size, binarySearch(array, 216))
        assertEquals(0, binarySearch(array, -216))
    }

    @Test
    fun binarySearchBigArray() {
        val random = Random(100)
        val array = (0..1000).map{random.nextInt()}.sorted()

        assertEquals((0..1000).toList(), array.map{binarySearch(array,it)})
    }

    //howManyNumbers
    @Test
    fun howManyNumbersBound() {
        val array = listOf(0, 8, 22, 37, 46, 58, 215)

        assertEquals(array.size, howManyNumbers(array, 0, 215))
    }

    @Test
    fun howManyNumbersInside() {
        val array = listOf(0, 8, 22, 37, 46, 58, 215)

        assertEquals(0, howManyNumbers(array, 59, 214))
        assertEquals(3, howManyNumbers(array, 9, 46))
    }

    @Test
    fun howManyNumbersOutOfBound() {
        val array = listOf(0, 8, 22, 37, 46, 58, 215)

        assertEquals(0, howManyNumbers(array, 216, 300))
        assertEquals(0, howManyNumbers(array, -1, -15))
    }

    @Test
    fun howManyNumbersLBiggerThanR() {
        val array = listOf(0, 8, 22, 37, 46, 58, 215)

        assertEquals(0, howManyNumbers(array, 58, 8))
    }

    @Test
    fun howManyNumbersUnsortedArray() {
        val array = listOf(0, 8, 22, 37, 46, 58, 215).shuffled()

        assertEquals(array.size, howManyNumbers(array, 0, 215))
        assertEquals(3, howManyNumbers(array, 9, 46))
    }

    //getSumOfPrime
    @Test
    fun getSumOfPrimeZero() {
        assertEquals(0L, getSumOfPrime(0))
    }

    @Test
    fun getSumOfPrimeOne() {
        assertEquals(2L, getSumOfPrime(1))
    }

    @Test
    fun getSumOfPrimeEleven() {
        assertEquals(160L, getSumOfPrime(11))
    }

    //findUnique
    @Test
    fun findUniqueNoUnique() {
        assertEquals(0, findUnique(array = listOf(2, 7, 4, 7, 2, 4)))
    }

    @Test
    fun findUniqueInside() {
        assertEquals(9, findUnique(array = listOf(0, 9, 5, 17, 5, 17, 0)))
    }

    @Test
    fun findUniqueBound() {
        assertEquals(1, findUnique(array = listOf(1, 7, 4, 7, 2, 2, 4)))
        assertEquals(9, findUnique(array = listOf(0, 5, 17, 5, 17, 0, 9)))
    }

    @Test
    fun findUniqueThreeEqualElements() {
        assertEquals(17, findUnique(array = listOf(0, 17, 5, 17, 17, 5, 0)))
    }

    @Test
    fun findUniqueAllNulls() {
        assertEquals(0, findUnique(array = listOf(0, 0, 0, 0, 0, 0, 0, 0)))
    }

    //ternarySearch
    @Test
    fun ternarySearchInside() {
        val array = listOf(0, 8, 1, 22, 37, 330, 46, 58, 215)

        assertEquals(array.max(), ternarySearch(array))
        assertNotEquals(215, ternarySearch(array))
    }

    @Test
    fun ternarySearchBound() {
        val array = listOf(0, 8, 1, 22, 37, 46, 58, 215)

        assertEquals(array.max(), ternarySearch(array))
    }

    @Test
    fun ternarySearchEqualElements() {
        val array = listOf(8, 8, 8, 8, 8, 8, 8, 8, 8)

        assertEquals(array.max(), ternarySearch(array))
    }

    @Test
    fun ternarySearchSomeNegativeElements() {
        val array = listOf(0, -8, 1, -22, 37, -330, 46, 58, 215)

        assertNotEquals(330, ternarySearch(array))
        assertEquals(array.max(), ternarySearch(array))
    }

    @Test
    fun ternarySearchNegativeMax() {
        val array = listOf(-8, -1, -22, -37, -330, -46, -58, -215)

        assertEquals(array.max(), ternarySearch(array))
    }

    @Test
    fun ternarySearchZeroMax() {
        val array = listOf(0, -8, -22, -1, -37, -330, -46, -58, -215)

        assertEquals(array.max(), ternarySearch(array))
    }
}