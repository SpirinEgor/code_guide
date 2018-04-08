package homework03

import org.junit.Test
import kotlin.test.assertEquals

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
    fun testContainsCorrectPairOnArrayContainingCorrectPair() {
        val array = intArrayOf(1, 2, 3, 4, 7, 8, 9 ,10)

        val result = containsCorrectPair(array.asList(), 7)
        val correctResult = true

        assertEquals(correctResult, result)
    }

    @Test
    fun testContainsCorrectPairOnArrayNotContainingCorrectPair() {
        val array = intArrayOf(1, 2, 3, 4, 7, 8, 9 ,10)

        val result = containsCorrectPair(array.asList(), 42)
        val correctResult = false

        assertEquals(correctResult, result)
    }

    @Test
    fun testContainsCorrectPairOnEmptyArray() {
        val array = intArrayOf()

        val result = containsCorrectPair(array.asList(), 42)
        val correctResult = false

        assertEquals(correctResult, result)
    }

    @Test
    fun testContainsCorrectPairOnOneElementArray() {
        val array = intArrayOf(1)

        val result = containsCorrectPair(array.asList(), 42)
        val correctResult = false

        assertEquals(correctResult, result)
    }
}
