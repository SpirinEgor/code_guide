package homework01

import org.junit.Assert
import org.junit.Test

class TasksKtTest {

    @Test
    fun binarySearch() {
        val array = listOf(0, 8, 22, 37, 46, 58, 215)

        Assert.assertEquals(2, binarySearch(array, 22))
        Assert.assertNotEquals(5, binarySearch(array, 22))
    }

    @Test
    fun howManyNumbers() {
        val array = listOf(0, 8, 22, 37, 46, 58, 215)

        Assert.assertEquals(0, howManyNumbers(array, 220, 300))
        Assert.assertEquals(3, howManyNumbers(array, 9, 46))
    }

    @Test
    fun getSumOfPrime() {
        Assert.assertEquals(2, getSumOfPrime(1))
        Assert.assertEquals(160, getSumOfPrime(11))
    }

    @Test
    fun findUnique() {
        Assert.assertEquals(0, findUnique(array = listOf(2, 7, 4, 7, 2, 4)))
        Assert.assertEquals(9, findUnique(array = listOf(0, 9, 5, 17, 5, 17, 0)))
    }

    @Test
    fun ternarySearch() {
        val array = listOf(0, 8, 1, 22, 37, 330, 46, 58, 215)

        Assert.assertEquals(330, ternarySearch(array))
        Assert.assertNotEquals(215, ternarySearch(array))
    }
}