package com.takeHomeTest.TakeHomeTest.Controller;


import com.takeHomeTest.TakeHomeTest.Entity.User;
import com.takeHomeTest.TakeHomeTest.Service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/search")
    public List<User> searchUser(@RequestParam String fullName) {
        return userService.searchUser(fullName);
    }
}
