package com.example.demo.controllers;

import com.example.demo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashSet;


@RestController
public class UserController {

    private final PollManager pollManager;

    @Autowired
    public UserController(PollManager pollManager) {
        this.pollManager = pollManager;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser (@RequestBody User user) {
        pollManager.getUsers().add(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users")
    public ResponseEntity<HashSet<User>> getUsers() {
        return ResponseEntity.ok(pollManager.getUsers());
    }

}
