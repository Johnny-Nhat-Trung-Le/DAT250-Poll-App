package com.example.demo.controllers;

import com.example.demo.entities.PollManager;
import com.example.demo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.UUID;

@CrossOrigin
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
        User newUser = pollManager.addUser(user);
        if (newUser == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newUser);
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

    @DeleteMapping("/{userId}/{pollId}/delete")
    public ResponseEntity<Void> deletePoll(@PathVariable UUID userId, @PathVariable UUID pollId) {
        if (userId == null || pollId == null) {
            return ResponseEntity.badRequest().build();
        }
        boolean deleted = pollManager.deletePoll(userId, pollId);
        if (!deleted) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

}
