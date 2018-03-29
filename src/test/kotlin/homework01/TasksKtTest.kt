package homework01

import org.junit.Test

import org.junit.Assert.*
import java.util.*

class TasksKtTest {

    fun dumbCountTriples(a: List<Int>, b: List<Int>, c: List<Int>, x: Int): Int {
        var sum = 0
        for (i in a) for (j in b) for (k in c) if (i + j + k == x) sum++
        return sum
    }

    @Test
    fun testBinarySearchOnPresentValues() {
        val random = Random(62352)
        val array = (0 until 10).map { random.nextInt() }.sorted()
        val indices = array.map { binarySearch(array, it) }
        assertArrayEquals(array.toIntArray(), indices.toIntArray())
    }

    @Test
    fun howManyNumbers() {
    }

    @Test
    fun getSumOfPrime() {
    }

    @Test
    fun countTriples() {
    }

    @Test
    fun findUnique() {
    }

    @Test
    fun ternarySearch() {
    }
}