package homework03

fun minimalDiffDivide(array: List<Int>) = 0

fun minimalScalarProduct(x: List<Int>, y: List<Int>) = 0

fun backpack(weight: List<Int>, cost: List<Int>, maxWeight: Int) = 0

/* Напомню задачу:
 * Дан отсортированный (по возрастанию) массив A и число x.
 * Определить, существуют ли i, j, такие, что:
 * A[i] + A[j] = x
 * время: O(n)
 * память: O(1)
 */
fun containsCorrectPair(a: List<Int>, x: Int): Boolean {
    var start = 0
    var end = a.size - 1
    // Очень хотелось сделать рекурсию,
    // но ладно уж, так и быть,
    // напишу эффективное решение с циклом
    while (start < end) {
        when {
            a[start] + a[end] > x -> end--
            a[start] + a[end] < x -> start++
            else -> return true
        }
    }
    return false
}
