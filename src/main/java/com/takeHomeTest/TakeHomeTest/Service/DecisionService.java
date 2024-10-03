package com.takeHomeTest.TakeHomeTest.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DecisionService {
    public String getDecision(double similarityScore) {
        if (similarityScore >= 80) {
            return "AUTO MATCH";
        } else if (similarityScore >= 60) {
            return "AMBIGUOUS";
        } else {
            return "AUTO NOT MATCH";
        }
    }
}
