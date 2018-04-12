const val MAX_N = 1_000_000
const val MAX_ITER = 1000

fun binarySearchInIncreasingArr(array: List<Int>, f: Int): Int {
    assert(array.size > 0)
    var left = 0
    var right = array.size - 1
    var mid = 0

    while (left < right) {
        mid = left + (right - left) / 2

        if (array[mid] == f)
            return mid

        if (array[mid] > f)
            right = mid
        else
            left = mid + 1
    }
    return -1
}

fun binarySearchInDecreasingArr(array: List<Int>, f: Int): Int{
    if (binarySearchInIncreasingArr(array.sorted(),f) > 0) {
        return array.size - binarySearchInIncreasingArr(array.sorted(), f) - 1
    }
    else {
        return -1
    }
}

fun binarySearch(array: List<Int>, f: Int): Int {
    when (array[0].compareTo(array[array.size-1])){
        0 -> return 0
        -1 -> return binarySearchInIncreasingArr(array, f)
        1 -> return binarySearchInDecreasingArr(array, f)
    }
    return -1
}



fun howManyNumbers(array: List<Int>, l: Int, r: Int): Int {
    assert(l <= r)
    var count = 0
    var myArr = array.sorted()
    for (i in l..r) {
        while (binarySearch(myArr, i) != -1) {
            count++
            myArr = myArr.minusElement(i)
        }
    }

    return  count
}

fun getSumOfPrime(k: Int): Long {
    if (k <= 0) return 0
    val used = BooleanArray(MAX_N)
    var sum = 0L
    var primesAmount = 0

    for (i in 2L until MAX_N) {
        if (!used[i.toInt()]) {
            sum += i
            primesAmount++
            if (primesAmount == k) {
                return sum
            }
            for (j in i * i until MAX_N step i) {
                used[j.toInt()] = true
            }
        }
    }

    throw Exception()
}

fun countTriples(a: List<Int>, b: List<Int>, c: List<Int>, x: Int): Int{
    var count = 0
    val cMap = HashMap<Int, Int>()

    for (cElement in c) {
        cMap[cElement] = (cMap[cElement] ?: 0) + 1
    }

    for (aElement in a) {
        for (bElement in b) {
            count += cMap[x - aElement - bElement] ?: 0
        }
    }

    return count
}

fun findUnique(array: List<Int>): Int = array.fold(0, { a: Int, b: Int -> a xor b })

fun ternarySearch(array: List<Int>): Int{
    var left = 0
    var right = array.size - 1
    var m1 = 0
    var m2 = 0

    while (right - left > 2){
        m1 = left + (right - left) / 3
        m2 = right - (right - left) / 3
        if (array[m1] < array[m2]) left = m1
        else right = m2
    }
    return left + (right - left) / 2
}
