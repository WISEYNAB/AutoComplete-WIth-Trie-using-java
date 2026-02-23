package com.example.AutocompleteWithTrie;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class TrieService {
    private final Trie trie = new Trie();

    @PostConstruct
    public void init(){
        List<String> words = List.of("apple", "apricot", "banana", "bandana", "car", "cat");
        for(String i : words){
            trie.insert(i);
        }

    }

    public List<String> findSuggestions(String prefix) {
        return trie.findWordsWithPrefix(prefix); // Method from previous step
    }

}
