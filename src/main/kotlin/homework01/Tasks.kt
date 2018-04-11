package homework01

const val MAX_N = 100000
const val MAX_ITER = 1000

fun binarySearch(array: List<Int>, f: Int): Int {
    var left = 0
    var right = array.size
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



fun howManyNumbers(array: List<Int>, l: Int, r: Int): Int {
    var count = 0

    for (i in l..r) {
        var MyArr = array
        while (binarySearch(MyArr, i) != -1) {
            count++
            MyArr = MyArr.minusElement(i)
        }
    }

    return  count
}

fun getSumOfPrime(k: Int): Long {

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
        cMap[cElement] = 1
    }

    for (aElement in a) {
        for (bElement in b) {
            if (cMap.contains(x - aElement - bElement) ) count++
        }
    }

    return count
}

fun findUnique(array: List<Int>): Int = array.fold(0, { a: Int, b: Int -> a xor b })

fun ternarySearch(array: List<Int>): Int{
    var left = 0
    var right = array.size
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