package homework03


fun minimalDiffDivide(array: List<Int>) = 0

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
