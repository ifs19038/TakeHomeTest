package com.takeHomeTest.TakeHomeTest.Service;

import com.takeHomeTest.TakeHomeTest.Entity.User;
import com.takeHomeTest.TakeHomeTest.Repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    public List<User> searchUser(String fullName) {
        return userRepository.findByFullNameIgnoreCase(fullName);
    }
}
