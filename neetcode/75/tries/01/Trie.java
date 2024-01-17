public class Trie {

    class TrieNode {
        
        TrieNode[] children;

        boolean isTerminal;
        
        TrieNode() {
            this.children = new TrieNode[26];
        }
    }

    private TrieNode head;

    public Trie() {
        this.head = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode cursor = head;
        for (int i = 0, sz = word.length(); i < sz; i++) {
            char ch = word.charAt(i);
            if (cursor.children[ch - 'a'] == null) {
                TrieNode child = new TrieNode();
                cursor.children[ch - 'a'] = child;
                cursor = child;
            } else {
                cursor = cursor.children[ch - 'a'];
            }
        }
        cursor.isTerminal = true;
    }

    public boolean search(String word) {
        TrieNode cursor = head;
        for (int i = 0, end = word.length(); i < end; i++) {
            char ch = word.charAt(i);
            cursor = cursor.children[ch - 'a'];
            if (cursor == null) {
                return false;
            }
        }
        return cursor != null && cursor.isTerminal;
    }
    
    public boolean startsWith(String prefix) {
        TrieNode cursor = head;
        for (int i = 0, sz = prefix.length(); i < sz; i++) {
            char ch = prefix.charAt(i);
            cursor = cursor.children[ch - 'a'];
            if (cursor == null) {
                return false;
            }
        }
        return true;
    }
}