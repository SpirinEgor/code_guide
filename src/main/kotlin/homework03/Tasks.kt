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

    return Math.abs(sumInList1 - sumInList2)
}

fun minimalScalarProduct(x: List<Int>, y: List<Int>): Int {
    var scalarProduct = 0
    val sortedX = x.sortedBy { it }
    val sortedY = y.sortedByDescending { it }

    for (i in 0 until sortedX.size) {
        scalarProduct += sortedX[i] * sortedY[i]
    }
    return scalarProduct
}

fun backpack(weight: List<Int>, cost: List<Int>, maxWeight: Int): Int {
    val a = Array(weight.size + 1, { IntArray(cost.size + 1) })

    for (k in 1..weight.size) {
        for (w in 1..maxWeight) {
            if (weight[k - 1] >= w) {
                a[k][w] = Math.max(a[k - 1][w], a[k - 1][w - weight[k - 1]] + cost[k - 1])
            } else {
                a[k][w] = a[k - 1][w]
            }
        }
    }
    return a[weight.size][maxWeight]
}
