package com.example.demo.controllers;

import com.example.demo.controllers.PollManager;
import com.example.demo.controllers.UserController;
import com.example.demo.entities.User;
import com.example.demo.entities.Vote;
import org.apache.coyote.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserControllerTest {

    private PollManager pollManager;
    private UserController userController;

    @BeforeEach
    void setUp() {
        pollManager = new PollManager();
        userController = new UserController(pollManager);
    }

    @Test
    void testCreateUser() {
        User user = new User("Alice", "mail");
        User user2 = new User("Bob", "mail2");
        ResponseEntity<User> result = userController.createUser(user);
        ResponseEntity<User> result2 = userController.createUser(user2);

        assertEquals(user, result.getBody());
        assertEquals(user2, result2.getBody());
        assertEquals(2, pollManager.getUsers().size());
        assertTrue(pollManager.getUsers().contains(user));
        assertTrue(pollManager.getUsers().contains(user2));
    }
}
