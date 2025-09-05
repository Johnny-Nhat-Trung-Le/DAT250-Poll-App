package com.example.demo.controllers;

import com.example.demo.entities.PollManager;
import com.example.demo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final PollManager pollManager;

    @Autowired
    public UserController(PollManager pollManager) { this.pollManager = pollManager; }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (user == null) {
            return ResponseEntity.noContent().build();
        }
        pollManager.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        if (id == null) {
            return ResponseEntity.notFound().build();
        }
        User user = pollManager.findUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        } return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<Collection<User>> getUsers() {
        return ResponseEntity.ok(pollManager.getUsers());
    }

}
