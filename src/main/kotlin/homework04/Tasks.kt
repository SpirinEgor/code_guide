package homework04

class Node {
    var isWord: Boolean = false
    val move = HashMap<Char, Node>()
}

class Trie(private val root: Node) {
    fun addWord(word: String) {
        word.fold(root, { curNode, c ->
            if (!curNode.move.containsKey(c)) {
                curNode.move[c] = Node()
            }
            curNode.move[c]!!
        }).isWord = true
    }

    fun checkWord(word: String): Boolean =
            word.fold(root, { curNode, c ->
                if (!curNode.move.containsKey(c)) {
                    return false
                }
                curNode.move[c]!!
            }).isWord

    fun getToNode(to: String): Node? =
            to.fold(root, { curNode, c ->
                if (!curNode.move.containsKey(c)) {
                    return null
                }
                curNode.move[c]!!
            })

    fun getRoot(): Node = root
}

fun differentSubstrings(s: String): Int = 0

fun manacker(s: String): Pair<List<Int>, List<Int>> = allPalindromes(s)

fun allPalindromes(s: String): Pair<List<Int>, List<Int>> {
    val oddPalindromes = MutableList(s.length, { 0 })
    val evenPalindromes = MutableList(s.length, { 0 })

    for (isEven in 0..1) {
        var l: Int = 0
        var r: Int = -1

        for (i in 0 until s.length) {
            var foundPalindromes = if (i > r) {
                1 - isEven
            } else {
                if (isEven == 0) {
                    Math.min(r - i + 1, oddPalindromes[l + r - i])
                } else {
                    Math.min(r - i, evenPalindromes[l + r - i - 1])
                }
            }

            while (i + foundPalindromes + isEven < s.length && i - foundPalindromes >= 0
                    && s[i + foundPalindromes + isEven] == s[i - foundPalindromes]) {
                ++foundPalindromes
            }
            if (isEven == 0) {
                oddPalindromes[i] = foundPalindromes
            } else {
                evenPalindromes[i] = foundPalindromes
            }
            if (i + foundPalindromes - (1 - isEven) > r) {
                r = i + foundPalindromes - (1 - isEven)
                l = i - foundPalindromes + 1
            }
        }
    }
    return Pair(oddPalindromes, evenPalindromes)
}

fun Trie.autocomplete(word: String): Int {
    var maxPrefix = 0
    var iter = 0

    word.fold(getRoot(), { curNode, c ->
        iter++
        if (!curNode.move.containsKey(c)) {
            addWord(word)
            return word.length
        }
        if (curNode.move.size > 1) {
            maxPrefix = iter
        }
        curNode.move[c]!!
    })
    return maxPrefix
}

fun autocomplete(words: List<String>): Int {
    val trie = Trie(Node())
    var totalSymbolsEntered = 0
    if (words[0] == "") {
        return 0
    }

    for (word in words) {
        totalSymbolsEntered += trie.autocomplete(word)
    }
    return totalSymbolsEntered
}


