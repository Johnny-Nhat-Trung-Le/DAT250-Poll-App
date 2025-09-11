package com.example.demo.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PollManagerTest {

    private PollManager pollManager;

    @BeforeEach
    void setUp() {
        pollManager = new PollManager();
    }


    @Test
    void userCreation() {
        User user1 = new User("Alice", "email1");
        User user2 = new User("Bob", "email1");
        User user3 = new User("Alice", "email2");

        assertTrue(pollManager.getUsers().isEmpty());

        pollManager.addUser(user1);
        pollManager.addUser(user2);
        pollManager.addUser(user3);

        assertEquals(2, pollManager.getUsers().size());
        assertTrue(pollManager.getUsers().contains(user1));
        assertFalse(pollManager.getUsers().contains(user2));
        assertTrue(pollManager.getUsers().contains(user3));
    }
}
