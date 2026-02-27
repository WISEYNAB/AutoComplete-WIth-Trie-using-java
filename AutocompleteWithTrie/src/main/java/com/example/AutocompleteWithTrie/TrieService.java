package com.example.AutocompleteWithTrie;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import java.util.List;

// Separate config class to define the shared Trie bean
@Configuration
class TrieConfig {

    @Bean  // Spring will create ONE Trie instance and share it everywhere
    public Trie trie() {
        Trie trie = new Trie();
        List<String> words = List.of("apple", "apricot", "banana", "bandana", "car", "cat");
        for (String word : words) {
            trie.insert(word);
        }
        return trie;
    }
}

@Service
public class TrieService {

    private final Trie trie;

    // Spring injects the shared Trie bean created above
    public TrieService(Trie trie) {
        this.trie = trie;
    }

    public List<String> findSuggestions(String prefix) {
        return trie.findWordsWithPrefix(prefix);
    }
}