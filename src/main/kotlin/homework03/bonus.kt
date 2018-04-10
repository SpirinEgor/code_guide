package homework03

/**
 * Имея два указателя на начало и хвост массива и каждый раз сверяя сумму значений по этим индексам,
 * мы передвигаем один из указателей вправо/влево до тех пор, пока они не пересекутся. Тем самым выполнится
 * в худшем случае n перемещений внутри массива (если такой пары нет) и n сравнений значений, что и дает оценку О(n)
 */

fun bonusTask(array: List<Int>, x: Int) : Pair<Int, Int>? {
    var left = 0
    var right = array.size - 1

    while (true) {
        val step = array[left] + array[right]
        when (step.compareTo(x)) {
            -1 -> left++
            0 -> return Pair(array[left], array[right])
            1 -> right--
        }

        if (left > right)
            break
    }

    return null
}