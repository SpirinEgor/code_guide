package homework03


fun minimalDiffDivide(array: List<Int>): Int{
    val sortedArray = array.sortedByDescending {it -> Math.abs(it)}
    var sumList1 = 0
    var sumList2 = 0

    for(i in 0 until sortedArray.size){
        if(sortedArray[i] >= 0){
            if(sumList1 >= sumList2)
                sumList2 += sortedArray[i]
            else
                sumList1 += sortedArray[i]
        }
        else{
            if(sumList1 >= sumList2)
                sumList1 += sortedArray[i]
            else
                sumList2 += sortedArray[i]
        }
    }

    return Math.abs(sumList1 - sumList2)
}

fun minimalScalarProduct(x: List<Int>, y: List<Int>): Int{
    var scalarProduct = 0
    val sortedX = x.sorted()
    val sortedY = y.sortedDescending()

    for (i in 0 until x.size){
        scalarProduct += sortedX[i] * sortedY[i]
    }

    return scalarProduct
}

fun backpack(weight: List<Int>, cost: List<Int>, maxWeight: Int): Int{
    //val backpack = Array(weight.size + 1, { Array(cost.size + 1, {0})})

    return 0
}