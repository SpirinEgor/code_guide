package homework01

const val MAX = 1000000

fun binarySearch(array: List<Int>, f: Int): Int {
    var fromIndex = 0
    var toIndex = array.size
    var index = 0

    while (fromIndex < toIndex){
    index = ((toIndex + fromIndex) / 2).toInt()

    if (array[index] == f) return index
    else
        if (array[index] < f) fromIndex = index - 1
        else toIndex = index + 1
    }

    return index
}

fun howManyNumbers(array: List<Int>, l: Int, r: Int): Int{
    val sortedArray = array.sorted()
    var fromIndex = 0
    var toIndex = array.size

    if(l >= sortedArray[0]) fromIndex = binarySearch(sortedArray, l)
    if(r <= sortedArray.last()) toIndex = binarySearch(sortedArray, r)

    return toIndex - fromIndex
}

fun getSumOfPrime(k: Int): Long{
    val arrayOfInclusion = arrayListOf<Boolean>()
    arrayOfInclusion.add(2, true)
    var sum = 0L
    var counterOfPrimes = 0

    while(counterOfPrimes < k){
        for(item in 2 until 100000){
            if(arrayOfInclusion[item]){
                sum += item
                counterOfPrimes++
                var i = item * item

                while(i < MAX){
                    arrayOfInclusion.add(i, false)
                    i += item
                }
            }
        }
    }

    return sum
}

fun countTriples(a: List<Int>, b: List<Int>, c: List<Int>, x: Int): Int = 0

fun findUnique(array: List<Int>): Int = array.fold(0, {x, y -> x xor  y})

fun ternarySearch(array: List<Int>): Int{
    var fromIndex = 0
    var toIndex = array.size
    var index1 = 0
    var index2 = 0

    while (toIndex - fromIndex > 0){
        index1 = (fromIndex + (toIndex - fromIndex) / 3).toInt()
        index2 = (fromIndex + 2 * (toIndex - fromIndex) / 3).toInt()

        if (array[index1] < array[index2]) fromIndex = index1
        else toIndex = index2
    }

    if (array[index1] >= array[index2]) {
        if (array[index1] > array[(index1 + index2) / 2])
            return index1
        else
            return (index1 + index2) / 2
    }
    else{
        if (array[index2] > array[(index1 + index2) / 2])
            return index2
        else
            return (index1 + index2) / 2
    }
}