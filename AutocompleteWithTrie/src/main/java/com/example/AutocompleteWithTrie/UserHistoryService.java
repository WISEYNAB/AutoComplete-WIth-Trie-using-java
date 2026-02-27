package com.example.AutocompleteWithTrie;

import java.util.Map;

public class UserHistoryService {

    private Map<String,Map<String,Integer>> historyData;

    public Integer getFrequency(String userId,String term){
        return historyData.getOrDefault(userId,Map.of())
                .getOrDefault(term,0);
    }

}





