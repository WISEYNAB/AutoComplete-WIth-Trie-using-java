package com.example.AutocompleteWithTrie;

import java.util.Comparator;
import java.util.List;

public class AutoCompleteService {
    private Trie trie;
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


}
