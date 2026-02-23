package com.example.AutocompleteWithTrie;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/autocomplete")
public class AutocompleteController {

    private final TrieService trieService;

    @Autowired
    public AutocompleteController(TrieService trieService) {
        this.trieService = trieService;
    }
    @GetMapping
    public List<String> getSuggestions(@RequestParam String prefix) {
        // We'll connect this to our Trie soon
        return trieService.findSuggestions(prefix);
    }
}
