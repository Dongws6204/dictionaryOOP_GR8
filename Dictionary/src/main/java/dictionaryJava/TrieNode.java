package dictionaryJava;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    private Map<Character, TrieNode> children;
    private String wordExplain;

    public TrieNode() {
        this.children = new HashMap<>();
        this.wordExplain = null;
    }

    public void insert(String word, String explain) {
        TrieNode node = this;
        for (char ch : word.toCharArray()) {
            node.children.putIfAbsent(ch, new TrieNode());
            node = node.children.get(ch);
        }
        node.wordExplain = explain;
    }

    public String search(String word) {
        TrieNode node = this;
        for (char ch : word.toCharArray()) {
            node = node.children.get(ch);
            if (node == null) {
                return null; // Word not found
            }
        }
        return node.wordExplain;
    }
    public void remove(String word) {
        removeHelper(this, word, 0);
    }

    private boolean removeHelper(TrieNode node, String word, int depth) {
        if (node == null) {
            return false; // Trie không có từ cần xóa
        }

        // Base case: đã duyệt hết tất cả các ký tự của từ
        if (depth == word.length()) {
            if (node.wordExplain != null) {
                // Từ được tìm thấy, xóa nghĩa của từ
                node.wordExplain = null;
                return true;
            }
            return false; // Từ không tồn tại trong Trie
        }

        char currentChar = word.charAt(depth);
        TrieNode childNode = node.children.get(currentChar);

        if (childNode == null) {
            return false; // Từ không tồn tại trong Trie
        }

        // Di chuyển đến ký tự tiếp theo trong từ
        boolean shouldRemoveChild = removeHelper(childNode, word, depth + 1);

        // Nếu nút con không có từ khác ngoài từ đang xóa,
        // và không có từ khác chia sẻ chung con đó,
        // có thể xóa nút con để giảm kích thước Trie
        if (shouldRemoveChild && childNode.children.isEmpty() && childNode.wordExplain == null) {
            node.children.remove(currentChar);
        }

        return shouldRemoveChild;
    }
    public void clear() {
        this.children.clear();
        this.wordExplain = null;
    }

}
