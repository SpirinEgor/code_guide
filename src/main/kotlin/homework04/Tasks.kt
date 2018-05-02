package homework04

class Node {
    var isWord: Boolean = false
    val move = HashMap<Char, Node>()
}

class Trie(val root: Node) {
    fun addWord(word: String) {
        word.fold(root, { curNode, c ->
            if (!curNode.move.containsKey(c)) {
                curNode.move[c] = Node()
            }
            curNode.move[c]!!
        }).isWord = true
    }

    fun getToNode(to: String): Node? =
            to.fold(root, { curNode, c ->
                if (!curNode.move.containsKey(c)) {
                    return null
                }
                curNode.move[c]!!
            })
}

fun differentSubstrings(s: String): Int = 0

fun manacker(s: String): Pair<List<Int>, List<Int>> = Pair(oddPalindromes(s), evenPalindromes(s))

fun oddPalindromes(s: String): List<Int> {
    val list = MutableList(s.length, { 0 })
    var l = 0
    var r = -1

    for (i in 0 until s.length) {
        var k = if (i > r) {
            1
        } else {
            Math.min(r - i + 1, list[l + r - i])
        }

        while (i + k < s.length && i - k >= 0 && s[i + k] == s[i - k]) {
            ++k
        }
        list[i] = k

        if (i + k - 1 > r) {
            r = i + k - 1
            l = i - k + 1
        }
    }

    return list.toList()
}

fun evenPalindromes(s: String): List<Int> {
    val list = MutableList(s.length, { 0 })
    var l = 0
    var r = -1

    for (i in 0 until s.length - 1) {
        var k = if (i > r) {
            0
        } else {
            Math.min(r - i, list[l + r - i - 1])
        }

        while (i + k + 1 < s.length && i - k >= 0 && s[i + k + 1] == s[i - k]) {
            ++k
        }
        list[i] = k

        if (i + k - 1 > r) {
            r = i + k
            l = i - k + 1
        }
    }

    return list.toList()
}

/**
 * Returns
 *      -1 if string doesn't exist in trie
 *      0 if any forward node has 2 move choices
 *      1 if autocomplete is available for use
 *
 */
fun Trie.isAutocompleteAvailable(substring: String, string: CharSequence): Int {
    var cur = getToNode(substring.substring(0 until substring.length)) ?: return -1
    var index = substring.length

    while (index < string.length) {
        if (cur.move.size > 1) {
            return 0
        }

        if (cur.move.size == 0) {
            return -1
        }

        val char = cur.move.keys.first().toChar()
        val ch = string.substring(index, index + 1).toCharArray()[0]
        index++
        if (char == ch) {
            cur = cur.move[ch]!!
        } else {
            return -1
        }
    }

    return when (cur.isWord) {
        true -> 1
        false -> -1
    }
}

fun autocomplete(words: List<String>): Int {
    val trie = Trie(Node())
    var totalSymbolsEntered = 0
    if (words[0] == "") {
        return 0
    }

    wordLoop@ for (s in words.iterator()) {
        for (i in 0 until s.length) {
            val substring = s.substring(0..i)
            when (trie.isAutocompleteAvailable(substring, s)) {
                -1 -> {
                    totalSymbolsEntered += s.length
                    trie.addWord(s)
                    continue@wordLoop
                }
                0 -> totalSymbolsEntered += 1
                1 -> {
                    totalSymbolsEntered += 1
                    continue@wordLoop
                }
                else -> {
                    return -1
                }
            }
        }

    }
    return totalSymbolsEntered
}


