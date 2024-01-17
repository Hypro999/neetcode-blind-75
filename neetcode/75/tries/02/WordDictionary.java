public class WordDictionary {

    class TrieNode {

        TrieNode[] children;

        boolean isTerminal;

        TrieNode() {
            this.children = new TrieNode[26];
        }
    }

    private TrieNode head;

    public WordDictionary() {
        this.head = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode cursor = head;
        for (int i = 0, sz = word.length(); i < sz; i++) {
            int key = word.charAt(i) - 'a';
            if (cursor.children[key] == null) {
                cursor.children[key] = new TrieNode();
            }
            cursor = cursor.children[key];
        }
        cursor.isTerminal = true;
    }

    public boolean search(String word) {
        return search(word, 0, head);
    }

    public boolean search(String word, int i, TrieNode cursor) {
        for (int sz = word.length(); i < sz; i++) {
            char ch = word.charAt(i);
            if (ch == '.') {
                return searchWildCard(word, i, cursor);
            }
            cursor = cursor.children[ch - 'a'];
            if (cursor == null) {
                return false;
            }
        }
        return cursor.isTerminal;
    }

    public boolean searchWildCard(String word, int i, TrieNode cursor) {
        for (int j = 0; j < 26; j++) {
            TrieNode subTrie = cursor.children[j];
            if (subTrie == null) {
                continue;
            }
            if (search(word, i + 1, subTrie)) {
                return true;
            }
        }
        return false;
    }
}