package com.example.demo.controllers;

import com.example.demo.entities.Poll;
import com.example.demo.entities.PollUserDTO;
import com.example.demo.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PollControllerTest {

    private PollManager pollManager;
    private PollController pollController;
    private User user;
    private Poll poll;

    @BeforeEach
    void setUp() {
        pollManager = new PollManager();
        pollController = new PollController(pollManager);
        user = new User("Alice", "email");
        pollManager.getUsers().add(user);
        poll = new Poll(1, user);
        pollManager.getPollUserMap().put(poll, user);
    }

    @Test
    void testGetPoll() {
        ResponseEntity<Set<Poll>> result = pollController.getPolls();
        Assertions.assertNotNull(result.getBody());
        assertTrue(result.getBody().contains(poll));
        assertEquals(user,pollManager.getPollUserMap().get(poll));
    }
}
