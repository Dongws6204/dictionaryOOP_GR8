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
}
