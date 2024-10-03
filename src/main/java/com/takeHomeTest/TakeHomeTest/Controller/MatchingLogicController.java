package com.takeHomeTest.TakeHomeTest.Controller;

import com.takeHomeTest.TakeHomeTest.Entity.User;
import com.takeHomeTest.TakeHomeTest.Service.FuzzyMatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/matching-logic")
public class MatchingLogicController {
    @Autowired
    private FuzzyMatchingService fuzzyMatchingService;

    @PostMapping("/match")
    public ResponseEntity<Double> matchUserUsingJaccard(@RequestBody User inputUser, @RequestParam String dbUserName) {
        double similarityScore = fuzzyMatchingService.calculateJaccardSimilarity(inputUser.getFullName(), dbUserName);
        return ResponseEntity.ok(similarityScore);
    }
}
