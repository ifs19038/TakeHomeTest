package com.takeHomeTest.TakeHomeTest.Service;

import com.takeHomeTest.TakeHomeTest.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCheckerService {
    @Autowired
    private RestTemplate restTemplate;

    public String checkUser(String fullName) {
        String userApiUrl = "http://localhost:2020/api/users/search?fullName=" + fullName;


        ResponseEntity<List<User>> userResponse = restTemplate.exchange(
                userApiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {}
        );
        List<User> users = userResponse.getBody();

        if (users == null || users.isEmpty()) {
            return "USER NOT DETECTED";
        }

        boolean foundAmbiguous = false;
        double ambiguousSimilarityScore = 0.0;


        for (User user : users) {
            String dbUserName = user.getFullName();
            String matchingApiUrl = "http://localhost:8080/api/matching-logic/match?dbUserName=" + fullName;


            ResponseEntity<Double> matchResponse = restTemplate.postForEntity(matchingApiUrl, user, Double.class);
            double similarityScore = matchResponse.getBody();


            String decisionApiUrl = "http://localhost:8080/api/decision/decide?similarityScore=" + similarityScore;
            ResponseEntity<String> decisionResponse = restTemplate.postForEntity(decisionApiUrl, null, String.class);
            String decision = decisionResponse.getBody();


            if ("AUTO MATCH".equals(decision)) {
//                return "USER DETECTED";
                return "USER DETECTED: " + dbUserName + ", Similarity Score: " + similarityScore;
            }


            if ("AMBIGUOUS".equals(decision)) {
                foundAmbiguous = true;
                ambiguousSimilarityScore = similarityScore;
            }
        }


        if (foundAmbiguous) {
//            return "CANNOT DETERMINE";
            return "CANNOT DETERMINE, Similarity Score: " + ambiguousSimilarityScore;
        }


        return "USER NOT DETECTED";
    }
}
