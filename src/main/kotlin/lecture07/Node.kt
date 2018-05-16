package lecture07

data class Node<K: Comparable<K>, V>(val key: K, var value: V) {
    var left: Node<K, V>? = null
    var right: Node<K, V>? = null
}
