package homework02

import homework01.*
import java.util.*

fun binarySearchTest() {
    val n: Int = Math.abs(Random().nextInt() % 1000)
    var array = Array(n, { i -> Random().nextInt() % 100000 })
    array.sort()
    for (i in 1..20) {
        val k: Int = Math.abs(Random().nextInt() % n)
        if (binarySearch(array.toList(), array[k]) != k) {
            println("Wrong")
        }
    }
}

fun howManyNumbersTest() {
    val n: Int = Math.abs(Random().nextInt() % 1000)
    var array = Array(n, { i -> Random().nextInt() % 100000 })
    array.sort()
    for (i in 1..20) {
        var left: Int = Math.abs(Random().nextInt() % n)
        var right: Int = Math.abs(Random().nextInt() % n)
        if (left > right) {
            left = right.also { right = left }
        }
        if (howManyNumbers(array.toList(), array[left], array[right]) != (right - left + 1)) {
            println("Wrong")
        }
    }
}

fun getSumOfPrimeTest() {
    val k: Int = Math.abs(Random().nextInt() % 10);
    for (i in 1..k) {
        val n: Int = Math.abs(Random().nextInt() % 100)
        if (getSumOfPrime(n) != getSumOfPrimeForTest(n)) {
            println("Wrong")
        }
    }

}

fun getSumOfPrimeForTest(k: Int): Long {
    val array = Array(100001, { i -> 0 })
    for (i in 2..array.size - 1 step 1) {
        if (array[i] == 0) {
            for (j in 2 * i..array.size - 1 step i) {
                array[j] = 1
            }
        }
    }
    var s: Long = 0
    var i: Int = 0
    var j: Int = 2
    while (i != k && j < array.size) {
        if (array[j] == 0) {
            s += j
            i++
        }
        j++
    }
    return s
}

fun countTriplesTest() {
    var a = Array(Math.abs(Random().nextInt() % 1000 + 1), { i -> Random().nextInt() % 100000 })
    var b = Array(Math.abs(Random().nextInt() % 1000 + 1), { i -> Random().nextInt() % 100000 })
    var c = Array(Math.abs(Random().nextInt() % 1000 + 1), { i -> Random().nextInt() % 100000 })
    a.sort()
    b.sort()
    c.sort()
    for (i in 1..20) {
        var n1: Int = Math.abs(Random().nextInt() % a.size)
        var n2: Int = Math.abs(Random().nextInt() % b.size)
        var n3: Int = Math.abs(Random().nextInt() % c.size)
        if (countTriples(a.toList(), b.toList(), c.toList(), a[n1] + b[n2] + c[n3]) != countTriplesForTest(a.toList(), b.toList(), c.toList(), a[n1] + b[n2] + c[n3])) {
            println("Wrong")
        }
    }
}

fun countTriplesForTest(a: List<Int>, b: List<Int>, c: List<Int>, x: Int): Int {
    a.toIntArray().sort()
    b.toIntArray().sort()
    c.toIntArray().sort()
    var k: Int = 0;
    for (i in 0..a.size - 1) {
        for (j in 0..b.size - 1) {
            if (binarySearch(c, x - (a[i] + b[j])) != c.size && c[binarySearch(c, x - (a[i] + b[j]))] == (x - a[i] - b[j])) {
                k++;
            }
        }
    }
    return k;
}

fun findUniqueTest() {
    val n: Int = Math.abs(Random().nextInt() % 1000)
    var a = Array(n, { i -> Random().nextInt() % 100000 })
    var array = Array(2 * n + 1, { i -> a[i % n] })
    if (findUnique(array.toList()) != array[0]) {
        println("Wrong")
    }
}

fun ternarySearchTest() {
    var a = Array(Math.abs(Random().nextInt() % 100 + 1), { i -> Random().nextInt() % 100000 })
    var b = Array(Math.abs(Random().nextInt() % 100 + 1), { i -> Random().nextInt() % 100000 })
    a.sort()
    b.sort()
    var array = Array(a.size + b.size, { i -> a[i % a.size] });
    for (i in a.size..array.size - 1) {
        array[i] = b[(b.size - 1) - (i - a.size)];
    }
    if (ternarySearchForTest(array.toList()) != ternarySearch(array.toList())) {
        println("Wrong")
    }

}

fun ternarySearchForTest(array: List<Int>): Int {
    var left: Int = 0
    var right: Int = array.size - 1
    while (right - left > 1) {
        var point1: Int = left + (right - left) / 3
        var point2: Int = left + 2 * (right - left) / 3

        if (array[point1] <= array[point1 + 1] && array[point2] <= array[point2 - 1]) {
            left = point1
            right = point2
        } else if (array[point1] >= array[point1 + 1] && array[point2] <= array[point2 - 1]) {
            right = point1
        } else if (array[point1] <= array[point1 + 1] && array[point2] >= array[point2 - 1]) {
            left = point2
        }
    }
    if (left > 0) {
        if (array[left] > array[left - 1] && array[left] > array[left + 1]) {
            return array[left]
        } else if (right != array.size - 1) {
            if (array[right] > array[right - 1] && array[right] > array[right + 1]) {
                return array[right]
            }
        } else {
            if (array[right] > array[right - 1]) {
                return array[right]
            }
        }
    } else {
        if (array[left] > array[left + 1]) {
            return array[left]
        }
    }
    return 0
}
