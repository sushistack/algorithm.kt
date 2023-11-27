import org.junit.jupiter.api.Tag

@Tag("Medium")
@Tag("Tries")
class NeetCodesTrie {
    class TrieNode(
        var children: MutableMap<Char, TrieNode> = mutableMapOf(),
        var isEnd: Boolean = false
    )

    class Trie(private val root: TrieNode = TrieNode()) {

        fun insert(word: String) {
            var cur = root
            for (char in word) {
                if (char !in cur.children) {
                    cur.children[char] = TrieNode()
                }
                cur = cur.children[char]!!
            }
            cur.isEnd = true
        }

        fun search(word: String): Boolean {
            var cur = root
            for (char in word) {
                if (char !in cur.children) {
                    return false
                }
                cur = cur.children[char]!!
            }
            return cur.isEnd
        }

        fun startsWith(prefix: String): Boolean {
            var cur = root
            for (char in prefix) {
                if (char !in cur.children) {
                    return false
                }
                cur = cur.children[char]!!
            }
            return true
        }
    }
}