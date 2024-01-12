package com.gamebacket.vercel.app.controller;


import com.gamebacket.vercel.app.entity.Games;
import com.gamebacket.vercel.app.service.impl.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/search")
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

    /**
     * searching games based on user input and returning all games that contain user input
     */
    @GetMapping("/games")
    public ResponseEntity<List<Games>> searchGameByName(@RequestParam String queryGames){
        List<Games> matchingGames = searchService.findByGameTitleContainingIgnoreCase(queryGames);
        return ResponseEntity.ok(matchingGames);
    }

    /**
     * Autocomplete search suggestions
     */
    @GetMapping("/game-options")
    public ResponseEntity<List<String>> gameGameOptions(){
        List<String> gameOptions = searchService.findAllGamesTitle();
        return ResponseEntity.ok(gameOptions);
    }

    @GetMapping("/users")
    public ResponseEntity<List<Object[]>> getRegisteredUsers(){
        List<Object[]> customers = searchService.findAllCustomers();
        return ResponseEntity.ok(customers);
    }

}
