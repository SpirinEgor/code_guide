package homework01

fun binarySearch(array: List<Int>, f: Int): Int {
    var fromIndex = 0
    var toIndex = array.size
    var index = 0
    while (fromIndex < toIndex){
    index = ((toIndex + fromIndex) / 2).toInt()
    if (array.get(index) == f) return index else
        if (array.get(index) < f) fromIndex = index - 1 else toIndex = index + 1
    }
    return index
}

fun howManyNumbers(array: List<Int>, l: Int, r: Int): Int{
    var counter = 0
    array.forEach{if(it in l..r) counter++}
    return counter
}

fun getSumOfPrime(k: Int): Long{
    val arrayOfInclusion = arrayListOf<Boolean>()
    arrayOfInclusion.add(2, true)
    var sum = 0.toLong()
    var counterOfPrimes = 0
    while(counterOfPrimes < k){
        for(item in 2 until 100000){
            if(arrayOfInclusion.get(item)){
                sum += item
                counterOfPrimes++
                for(i in item * item until 100000){
                    arrayOfInclusion.add(i, true)
                }
            }
        }
    }

    return sum
}

fun countTriples(a: List<Int>, b: List<Int>, c: List<Int>, x: Int): Int = 0

fun findUnique(array: List<Int>): Int {
    for (x in array) {
        (array as ArrayList).remove(x)
        if (!array.contains(x)) return x
        else (array).add(x)
    }
    return -1
}

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
    return (index1 + index2) / 2
}