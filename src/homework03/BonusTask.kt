package homework03

fun bonusTask(array: List<Int>, x: Int) {
    var leftNumber: Int = 0
    var rightNumber: Int = array.size - 1
    while (leftNumber < rightNumber && array[leftNumber] + array[rightNumber] != x) {
        while (array[leftNumber] + array[rightNumber] > x) {
            rightNumber--;
        }
        if (array[leftNumber] + array[rightNumber] < x) {
            leftNumber++;
        }
    }
    if (leftNumber < rightNumber) {
        println("${array[leftNumber]} ${array[rightNumber]}")
    } else {
        println("Wrong")
    }

}