package com.example.AutocompleteWithTrie;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;

@Service  // Now Spring manages this bean too
public class AutoCompleteService {

    private final Trie trie;
    private final UserHistoryService userHistoryService;

    // Constructor injection — the BEST way to inject dependencies in Spring
    // Spring sees this constructor, looks up beans of each parameter type,
    // and passes them in automatically
    public AutoCompleteService(Trie trie, UserHistoryService userHistoryService) {
        this.trie = trie;
        this.userHistoryService = userHistoryService;
    }

    public List<String> getPersonalizedSuggestions(String prefix, String userId) {
        List<String> allSuggestions = trie.findWordsWithPrefix(prefix);

        allSuggestions.sort(Comparator.comparing(
                (String term) -> userHistoryService.getFrequency(userId, term)
        ).reversed());

        return allSuggestions;
    }

    @Cacheable("autocompleteSuggestions")
    public List<String> findSuggestions(String prefix) {
        System.out.println("Querying Trie for prefix: " + prefix);
        return trie.findWordsWithPrefix(prefix);
    }
}