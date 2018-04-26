package homework01

import org.junit.Test

import org.junit.Assert.*
import java.util.*
import kotlin.collections.HashMap
import kotlin.test.assertTrue

class TasksKtTest {

    /*
    Тесты написаны в том самом Arrange-Act-Assert
    Основаны на конкретной реализации функций. Например,
    подразумевается, что для несуществующего элемента
    binarySearch() возвращает insertionPoint (без минуса)
    Можно было бы потестировать еще: случай с повторяющимися
    элементами, неотсортированный массив и т.п., т.е. проверять,
    что ваша реализация правильно (как вы задумали) обрабатывает
    все эти случаи.
    В каждой функции можно потестировать еще много особых случаев,
    но, думаю, суть понятна.
    */

    private val SEED = 100L


    // binarySearch()
    @Test
    fun testBinarySearchMiddleElement() {
        val values = listOf(1, 2, 3, 4, 5)

        val result = binarySearch(values, 3)
        val expected = 2

        assertEquals(expected, result)
    }

    @Test
    fun testBinarySearchEdgeElements() {
        val values = listOf(1, 2, 3, 4, 5)

        val resultLeft = binarySearch(values, 1)
        val resultRight = binarySearch(values, 5)
        val expectedLeft = 0
        val expectedRight = values.size - 1

        assertEquals(expectedLeft, resultLeft)
        assertEquals(expectedRight, resultRight)
    }

    @Test
    fun testBinarySearchNotContainingElements() {
        val values = listOf(1, 2, 3, 4, 5)

        val resultLeft = binarySearch(values, -1)
        val resultRight = binarySearch(values, 10)
        val expectedLeft = 0
        val expectedRight = values.size

        assertEquals(expectedLeft, resultLeft)
        assertEquals(expectedRight, resultRight)
    }

    @Test
    fun testBinarySearchAllNegativeElements() {
        val values = (-10..-1).toList()

        val results = (-10..-1).map { binarySearch(values, it)}
        val expected = (0..9).toList()


        assertEquals(expected, results)
    }

    @Test
    fun testBinarySearchManyRandomElements() {
        val random = Random(SEED)
        val elementsAmount = 1000
        val values = (0 until elementsAmount).map {random.nextInt()}.sorted()

        val results = values.map { binarySearch(values, it)}
        val expected = (0 until elementsAmount).toList()

        assertEquals(expected, results)
    }


    // howManyNumbers()

    @Test
    fun testHowManyNumbersOneElement() {
        val values = listOf(1).shuffled(Random(SEED))
        val left = 0
        val right = 2

        val results = howManyNumbers(values, left, right)
        val expected = 1

        assertEquals(expected, results)
    }

    @Test
    fun testHowManyNumbersContainingEdgesShuffled() {
        val values = listOf(1, 2, 3, 4, 5).shuffled(Random(SEED))
        val left = 2
        val right = 4

        val results = howManyNumbers(values, left, right)
        val expected = 3

        assertEquals(expected, results)
    }

    @Test
    fun testHowManyNumbersContainingEdgesSorted() {
        val values = listOf(1, 2, 3, 4, 5)
        val left = 2
        val right = 4

        val results = howManyNumbers(values, left, right)
        val expected = 3

        assertEquals(expected, results)
    }

    @Test
    fun testHowManyNumbersNotContainingEdgesShuffled() {
        val values = listOf(1, 3, 5).shuffled(Random(SEED))
        val left = 2
        val right = 4

        val results = howManyNumbers(values, left, right)
        val expected = 1

        assertEquals(expected, results)
    }

    @Test
    fun testHowManyNumbersNotContainingEdgesSorted() {
        val values = listOf(1, 3, 5)
        val left = 2
        val right = 4

        val results = howManyNumbers(values, left, right)
        val expected = 1

        assertEquals(expected, results)
    }

    @Test
    fun testHowManyNumbersWrongOrderEdgesShuffled() {
        val values = listOf(1, 2, 3, 4, 5).shuffled(Random(SEED))
        val left = 4
        val right = 2

        val results = howManyNumbers(values, left, right)
        val expected = 0

        assertEquals(expected, results)
    }

    @Test
    fun testHowManyNumbersEqualContainingEdgesShuffled() {
        val values = listOf(1, 2, 3, 4, 5).shuffled(Random(SEED))
        val left = 3
        val right = 3

        val results = howManyNumbers(values, left, right)
        val expected = 1

        assertEquals(expected, results)
    }

    @Test
    fun testHowManyNumbersEqualNotContainingEdgesShuffled() {
        val values = listOf(1, 2, 4, 5).shuffled(Random(SEED))
        val left = 3
        val right = 3

        val results = howManyNumbers(values, left, right)
        val expected = 0

        assertEquals(expected, results)
    }

    @Test
    fun testHowManyNumbersRandomElements() {
        val random = Random(SEED)
        val elementsAmount = 1000
        val values = (0 until elementsAmount).map {random.nextInt()}
        val valuesSorted = values.sorted()
        val leftRandom = random.nextInt()
        val rightRandom = random.nextInt()
        val leftTwoElements = valuesSorted[500]
        val rightTwoElements = valuesSorted[501]

        val resultsAllArray = howManyNumbers(values, values.min()!!, values.max()!!)
        val expectedAllArray = values.size
        val resultsRandomBounds = howManyNumbers(values, leftRandom, rightRandom)
        val expectedRandomBounds = values.filter { it in leftRandom..rightRandom }.size
        val resultsTwoElements = howManyNumbers(values, leftTwoElements, rightTwoElements)
        val expectedTwoElements = 2

        assertEquals(expectedAllArray, resultsAllArray)
        assertEquals(expectedRandomBounds, resultsRandomBounds)
        assertEquals(resultsTwoElements, expectedTwoElements)
    }


    // getSumOfPrime()

    @Test
    fun testGetSumOfPrimeZero() {
        val amount = 0

        val result = getSumOfPrime(amount)
        val expected = 0L

        assertEquals(expected, result)
    }

    @Test
    fun testGetSumOfPrimeTwo() {
        val amount = 2

        val result = getSumOfPrime(amount)
        val expected = 5L

        assertEquals(expected, result)
    }

    @Test
    fun testGetSumOfPrimeTen() {
        val amount = 10

        val result = getSumOfPrime(amount)
        val expected = (2..1000).filter { value -> (2 until value).all { value % it > 0 }}
                                .take(amount).map {it.toLong()}.sum()

        assertEquals(expected, result)
    }

    @Test
    fun testGetSumOfPrime1k() {
        val amount = 1000

        val result = getSumOfPrime(amount)
        val expected = (2..10000).filter { value -> (2 until value).all { value % it > 0 }}
                                 .take(amount).map {it.toLong()}.sum()

        assertEquals(expected, result)
    }

    @Test
    fun testGetSumOfPrime10kOverflow() {
        val amount = 75000

        val result = getSumOfPrime(amount)

        assertTrue("Sum must be > 0. Actual sum: $result ") { result > 0 }
    }

    @Test
    fun testGetSumOfPrimeTooMany() {
        val amount = 1000000

        val result = getSumOfPrime(amount)
        val expected = -1L

        assertEquals(expected, result)
    }


    // countTriples()

    @Test
    fun testCountTriplesLessThanMinimum() {
        val a = listOf(1, 2)
        val b = listOf(2, 3)
        val c = listOf(0)
        val minValue = a.min()!! + b.min()!! + c.min()!!

        val resultLessThanMinimum = homework01.countTriples(a, b, c, minValue - 1)
        val resultZero = countTriples(a, b, c, 0)
        val expected = 0

        assertEquals(expected, resultLessThanMinimum)
        assertEquals(expected, resultZero)
    }


    @Test
    fun testCountTriplesMoreThanMaximum() {
        val a = listOf(1, 2)
        val b = listOf(2, 3)
        val c = listOf(0)
        val maxValue = a.max()!! + b.max()!! + c.max()!!

        val resultMoreThanMaximum = countTriples(a, b, c, maxValue + 1)
        val result1k = countTriples(a, b, c, 1000)
        val expected = 0

        assertEquals(expected, resultMoreThanMaximum)
        assertEquals(expected, result1k)
    }

    private fun allSumsCount(a: List<Int>, b: List<Int>, c: List<Int>): HashMap<Int, Int> {
        val sumsCount = HashMap<Int, Int>()
        for(elemA in a)
            for(elemB in b)
                for(elemC in c) {
                    val sum = elemA + elemB + elemC
                    sumsCount[sum] = (sumsCount[sum] ?: 0) + 1
                }
        return sumsCount
    }

    @Test
    fun testCountTriplesSmallArrays() {
        val a = listOf(1, 2, 3)
        val b = listOf(2, 3, 4)
        val c = listOf(3, 4, 5)
        val countsExpected = allSumsCount(a, b, c)

        val countsResults = HashMap<Int, Int>()
        for(sum in countsExpected.keys)
            countsResults[sum] = countTriples(a, b, c, sum)

        for(sum in countsExpected.keys)
            assertEquals(countsExpected[sum], countsResults[sum])
    }

    @Test
    fun testCountTriplesOneElementArrays() {
        val a = listOf(1, 1, 1)
        val b = listOf(2, 2, 2)
        val c = listOf(3, 3, 3)
        val countsExpected = allSumsCount(a, b, c)

        val countsResults = HashMap<Int, Int>()
        for(sum in countsExpected.keys)
            countsResults[sum] = countTriples(a, b, c, sum)

        for(sum in countsExpected.keys)
            assertEquals(countsExpected[sum], countsResults[sum])
    }

    @Test
    fun testCountTriplesWithNegativeElements() {
        val a = listOf(1, -2, -3)
        val b = listOf(0, -1, 2)
        val c = listOf(-1, 0, 1)
        val countsExpected = allSumsCount(a, b, c)

        val countsResults = HashMap<Int, Int>()
        for(sum in countsExpected.keys)
            countsResults[sum] = countTriples(a, b, c, sum)

        for(sum in countsExpected.keys)
            assertEquals(countsExpected[sum], countsResults[sum])
    }


    // findUnique()

    private fun findUniqueBruteForce(arr: List<Int>): Int {
        val map = HashMap<Int, Int>()
        arr.forEach { map[it] = (map[it] ?: 0) + 1 }
        return map.filter {it.value != 2}.keys.toList()[0]
    }

    @Test
    fun testFindUniqueSmallArray() {
        val values = listOf(1, 1, 3)

        val result = findUnique(values)
        val expected = 3

        assertEquals(expected, result)
    }

    @Test
    fun testFindUniqueOneElement() {
        val values = listOf(3)

        val result = findUnique(values)
        val expected = 3

        assertEquals(expected, result)
    }

    @Test
    fun testFindUniqueNegativeElement() {
        val values = listOf(-1, 0, 0, 1, 1)

        val result = findUnique(values)
        val expected = -1

        assertEquals(expected, result)
    }

    @Test
    fun testFindUniqueRandomElements() {
        val random = Random(SEED)
        val elementsAmount = 1000
        val values = (0 until elementsAmount).map {random.nextInt()}.toMutableList()
        values.addAll(values.take(values.size - 1))

        val result = findUnique(values)
        val expected = findUniqueBruteForce(values)

        assertEquals(expected, result)
    }


    // ternarySearch()

    @Test
    fun testTernarySearchAscending() {
        val values = (1..4).toList()

        val result = ternarySearch(values)
        val expected = values.max()

        assertEquals(expected, result)
    }

    @Test
    fun testTernarySearchDescending() {
        val values = (4 downTo 1).toList()

        val result = ternarySearch(values)
        val expected = values.max()

        assertEquals(expected, result)
    }

    @Test
    fun testTernarySearchUnimodalFewElements() {
        val values = listOf(1, 2, 3, 2, 1)

        val result = ternarySearch(values)
        val expected = values.max()

        assertEquals(expected, result)
    }

    @Test
    fun testTernarySearchUnimodalCloseToEdges() {
        val values = listOf(1, 2, 5, 7, 3)

        val result = ternarySearch(values)
        val expected = values.max()

        assertEquals(expected, result)
    }
}