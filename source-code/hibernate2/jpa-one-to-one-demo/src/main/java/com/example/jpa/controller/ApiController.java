package com.example.jpa.controller;


import com.example.jpa.model.User;
import com.example.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public ResponseEntity<String> getUsers() {
        return ResponseEntity.ok().body(userRepository.findById(1l).toString());
    }
}
