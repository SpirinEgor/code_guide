package homework03


fun minimalDiffDivide(array: List<Int>): Int {
    var sumInList1 = 0
    var sumInList2 = 0

    val sortedList = if (Math.abs(array.min()!!) >= Math.abs(array.max()!!)) {
        array.sortedBy { it }
    } else {
        array.sortedByDescending { it }
    }

    for (i in 0 until sortedList.size) {
        val addedElement = sortedList[i]

        if (sumInList1 >= sumInList2) {
            sumInList2 += addedElement
        } else {
            sumInList1 += addedElement
        }
    }

    if (sumInList1 == 0)
        return sumInList2

    if (sumInList2 == 0)
        return sumInList1

    val diff1 = sumInList1 - sumInList2
    val diff2 = sumInList2 - sumInList1


    return if (diff1 <= diff2) {
        diff1
    } else {
        diff2
    }
}

fun minimalScalarProduct(x: List<Int>, y: List<Int>) = 0

fun backpack(weight: List<Int>, cost: List<Int>, maxWeight: Int): Int {
    val a = Array(weight.size + 1, { IntArray(cost.size + 1) })

    for (k in 1..weight.size) {
        for (w in 1..maxWeight) {
            if (weight[k - 1] >= w) {
                a[k][w] = Math.max(a[k - 1][w], a[k - 1][w - weight[k - 1] + cost[k - 1]])
            } else {
                a[k][w] = a[k - 1][w]
            }
        }
    }
    return a[weight.size][maxWeight]
}
