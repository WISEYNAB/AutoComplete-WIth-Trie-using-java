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
    private final AutoCompleteService autoCompleteService;

    @Autowired
    public AutocompleteController(TrieService trieService,AutoCompleteService autoCompleteService) {
        this.trieService = trieService;
        this.autoCompleteService = autoCompleteService;
    }
    @GetMapping
    public List<String> getSuggestions(@RequestParam String prefix) {
        return trieService.findSuggestions(prefix);
    }

    @GetMapping("/personalized")
    public List<String> getPersonalizedSuggestions(@RequestParam String prefix,
                                                   @RequestParam String userId) {
        return autoCompleteService.getPersonalizedSuggestions(prefix, userId);
    }
}
