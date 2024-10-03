package com.takeHomeTest.TakeHomeTest.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.text.similarity.JaccardSimilarity;

@Service
@RequiredArgsConstructor
public class FuzzyMatchingService {
    private final JaccardSimilarity jaccardSimilarity;

    public FuzzyMatchingService() {
        this.jaccardSimilarity = new JaccardSimilarity();
    }
    public double calculateJaccardSimilarity(String input, String dbName) {
        double similarity = jaccardSimilarity.apply(input, dbName);

        return similarity * 100;
    }
}