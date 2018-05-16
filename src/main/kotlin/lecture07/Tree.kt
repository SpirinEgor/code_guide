package lecture07

abstract class Tree<K: Comparable<K>, V>(var root: Node<K, V>?) {

    fun find(key: K): V? {
        var curNode = root
        while (curNode != null) {
            when (key.compareTo(curNode.key)) {
                0 -> return curNode.value
                1 -> curNode = curNode.right
                -1 -> curNode = curNode.left
            }
        }
        return null
    }

    fun check(key: K): Boolean = find(key) != null

    fun insert(key: K, value: V): Node<K, V>? {
        if (root == null) {
            root = Node(key, value)
            return root
        }
        var curNode = root
        var parentNode = root
        while (curNode != null) {
            when (key.compareTo(curNode.key)) {
                0 -> {
                    curNode.value = value
                    return curNode
                }
                1 -> {
                    parentNode = curNode
                    curNode = curNode.right
                }
                -1 -> {
                    parentNode = curNode
                    curNode = curNode.left
                }
            }
        }
        when (parentNode!!.key.compareTo(key)) {
            1 -> {
                parentNode.left = Node(key, value)
                return parentNode.left
            }
            -1 -> {
                parentNode.right = Node(key, value)
                return parentNode.right
            }
        }
        return null
    }

    fun delete(key: K): Node<K, V>? {
        if (find(key) == null) {
            return null
        }
        var parentNode = root!!
        var curNode = root!!
        loop@ while (curNode.key != key) {
            when (key.compareTo(curNode.key)) {
                1 -> {
                    parentNode = curNode
                    curNode = curNode.right!!
                }
                -1 -> {
                    parentNode = curNode
                    curNode = curNode.left!!
                }
            }
        }
        // if curNode has 0 or child, then just delete it
        if (curNode.left == null || curNode.right == null) {
            if (curNode.left == null) {
                if (parentNode.left!!.key == key) {
                    parentNode.left = curNode.right
                } else {
                    parentNode.right = curNode.right
                }
            } else {
                if (parentNode.left!!.key == key) {
                    parentNode.left = curNode.left
                } else {
                    parentNode.right = curNode.left
                }
            }
            return curNode
        }
        // otherwise swap del node with next node
        // get node with next key
        var nextNode = curNode.right!!
        parentNode = curNode
        while (nextNode.left != null) {
            parentNode = nextNode
            nextNode = nextNode.left!!
        }
        // swap equal to change value
        val tmp = curNode.value
        curNode.value = nextNode.value
        nextNode.value = tmp
        parentNode.left = nextNode.right
        return nextNode
    }

}