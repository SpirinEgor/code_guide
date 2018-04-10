package homework03

import org.junit.Test
import kotlin.test.assertNull
import kotlin.test.assertTrue

class TasksKtTest {

    @Test
    fun minimalDiffDivide() {
    }

    @Test
    fun minimalScalarProduct() {
    }

    @Test
    fun backpack() {
    }

    @Test
    fun findPairOfIntegersWhichSumEqualsToXIfSumContains() {
        val array = listOf(1, 2, 5, 8, 13, 25, 45, 62, 76, 87)
        val result = findPairsWithSumX(array, 70)
        val test = bonusTask(array, 70)
        assertTrue(result!!.contains(test))
    }

    @Test
    fun findPairOfIntegersWhichSumEqualsToXIfSumNotContains() {
        val array = listOf(1, 2, 5, 8, 13, 25, 45, 62, 76, 87)

        val result = null
        val test = bonusTask(array, 2500)
        assertTrue(test == result)
    }

    private fun findPairsWithSumX(array: List<Int>, x: Int): List<Pair<Int, Int>>? {
        val pairs: MutableList<Pair<Int, Int>> = mutableListOf()
        for (i in 0 until array.size - 1) {
            for (j in i + 1 until array.size) {
                if (array[i] + array[j] == x) {
                    pairs.add(Pair(array[i], array[j]))
                }
            }
        }

        if (pairs.size == 0) {
            return null
        }

        return pairs
    }

}
