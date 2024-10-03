package com.takeHomeTest.TakeHomeTest.Controller;

import com.takeHomeTest.TakeHomeTest.Service.UserCheckerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/user-checker")
public class UserCheckerController {

    @Autowired
    private UserCheckerService userCheckerService;

    @PostMapping("/check")
    public ResponseEntity<String> checkUser(@RequestParam String fullName) {
        String result = userCheckerService.checkUser(fullName);
        return ResponseEntity.ok(result);
    }
}


