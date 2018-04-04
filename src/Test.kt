import java.util.*

class Test {
    fun binarySearchTest() {
        val n: Int = Math.abs(Random().nextInt() % 1000)
        var array = Array(n, { i -> Random().nextInt() % 100000 })
        array.sort()
        for (i in 1..20) {
            val k: Int = Math.abs(Random().nextInt() % n)
            if (binarySearch(array.toList(), array[k]) != k) {
                println("Fuck")
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
                println("Fuck")
                println("$right $left ${howManyNumbers(array.toList(), left, right)}")
            }
        }
    }

    fun countTriplesTest() {
        var a = Array(Math.abs(Random().nextInt() % 1000), { i -> Random().nextInt() % 100000 })
        var b = Array(Math.abs(Random().nextInt() % 1000), { i -> Random().nextInt() % 100000 })
        var c = Array(Math.abs(Random().nextInt() % 1000), { i -> Random().nextInt() % 100000 })
        a.sort()
        b.sort()
        c.sort()
        for (i in 1..20) {
            var n1: Int = Math.abs(Random().nextInt() % a.size)
            var n2: Int = Math.abs(Random().nextInt() % b.size)
            var n3: Int = Math.abs(Random().nextInt() % c.size)
            if (countTriples(a.toList(), b.toList(), c.toList(), a[n1] + b[n2] + c[n3]) != 1) {
                println("Fuck")
            }
        }
    }

    fun ternarySearchTest() {
        var a = Array(Math.abs(Random().nextInt() % 10), { i -> Random().nextInt() % 100000 })
        var b = Array(Math.abs(Random().nextInt() % 10), { i -> Random().nextInt() % 100000 })
        a.sort()
        b.sort()
        var array = IntArray(a.size + b.size)
        for (i in 0..a.size - 1) {
            array[i] = a[i]
        }
        for (i in 0..b.size - 1) {
            array[a.size + i] = b[b.size - 1 - i]
        }

        if (ternarySearch(array.toList()) != array[a.size-1]) {
            if (ternarySearch(array.toList()) != array[a.size]){
                println("Fuck")
            }
        }
    }
}