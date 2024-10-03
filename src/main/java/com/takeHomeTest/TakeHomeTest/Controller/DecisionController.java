package com.takeHomeTest.TakeHomeTest.Controller;

import com.takeHomeTest.TakeHomeTest.Service.DecisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/decision")
public class DecisionController {
    @Autowired
    private DecisionService decisionService;

    @PostMapping("/decide")
    public ResponseEntity<String> decideMatch(@RequestParam double similarityScore) {
        String decision = decisionService.getDecision(similarityScore);
        return ResponseEntity.ok(decision);
    }
}
