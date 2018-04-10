package homework03

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