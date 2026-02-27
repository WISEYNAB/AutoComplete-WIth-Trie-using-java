package com.example.AutocompleteWithTrie;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
@Service
public class AutoCompleteService {
    private Trie trie;

    public AutoCompleteService(Trie trie){
        this.trie = trie;
    }

    public UserHistoryService userHistoryService;


    public List<String> getPersonalizedSuggestions(String prefix, String userId) {
        // 1. Get all matches from the Trie
        List<String> allSuggestions = trie.findWordsWithPrefix(prefix);

        // 2. Rank the matches based on user history
        allSuggestions.sort(Comparator.comparing(
                (String term) -> userHistoryService.getFrequency(userId, term)
        ).reversed()); // reversed() for descending order

        return allSuggestions;
    }

    @Cacheable("autocompleteSuggestions")
    public List<String> findSuggestions(String prefix) {
        // This log will only print when the method is actually executed,
        // not when the result is served from the cache.
        System.out.println("Querying Trie for prefix: " + prefix);
        return trie.findWordsWithPrefix(prefix);
    }

}
