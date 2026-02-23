package com.example.AutocompleteWithTrie;

import java.util.*;

public class Trie {

    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEndOfWord = false;
    }

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Insert a word into trie
    public void insert(String word) {
        TrieNode curr = root;

        for (char c : word.toCharArray()) {
            int index = c - 'a';

            if (curr.children[index] == null) {
                curr.children[index] = new TrieNode();
            }

            curr = curr.children[index];
        }

        curr.isEndOfWord = true;
    }

    // Find all words with given prefix
    public List<String> findWordsWithPrefix(String prefix) {
        List<String> result = new ArrayList<>();
        TrieNode curr = root;

        // Step 1: Traverse till prefix ends
        for (char c : prefix.toCharArray()) {
            int index = c - 'a';

            if (curr.children[index] == null) {
                return result; // No words with this prefix
            }

            curr = curr.children[index];
        }

        // Step 2: DFS from prefix node
        dfs(curr, prefix, result);

        return result;
    }

    private void dfs(TrieNode node, String word, List<String> result) {
        if (node.isEndOfWord) {
            result.add(word);
        }

        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                char nextChar = (char) (i + 'a');
                dfs(node.children[i], word + nextChar, result);
            }
        }
    }
}
