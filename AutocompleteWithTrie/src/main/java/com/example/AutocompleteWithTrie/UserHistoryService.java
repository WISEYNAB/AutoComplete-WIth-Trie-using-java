package com.example.AutocompleteWithTrie;

import org.springframework.stereotype.Service;
import java.util.Map;

@Service  // Tells Spring: "Manage this class as a Bean"
public class UserHistoryService {

    // Hardcoded demo data for now — in real apps this comes from a DB
    private final Map<String, Map<String, Integer>> historyData = Map.of(
            "user1", Map.of("apple", 5, "apricot", 2),
            "user2", Map.of("banana", 3, "car", 7)
    );

    public Integer getFrequency(String userId, String term) {
        return historyData
                .getOrDefault(userId, Map.of())
                .getOrDefault(term, 0);
    }
}