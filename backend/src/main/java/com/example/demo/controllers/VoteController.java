package com.example.demo.controllers;

import com.example.demo.entities.PollManager;
import com.example.demo.entities.User;
import com.example.demo.entities.Vote;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/votes")
public class VoteController {

    private final PollManager pollManager;

    @Autowired
    public VoteController(PollManager pollManager) { this.pollManager = pollManager; }

    @GetMapping
    public ResponseEntity<Collection<Vote>> getVotes(@RequestParam(required = false) UUID userId) {
        return ResponseEntity.ok(getVotesWithCache(userId));
    }

    @Cacheable(value = "votes", key = "#userId")
    public Collection<Vote> getVotesWithCache(UUID userId) {
        if (userId == null) {
            return pollManager.getVotes();
        }
        return pollManager.findVotesByUserId(userId);
    }

    @GetMapping("/{userId}/recent")
    public ResponseEntity<Vote> getRecentVote(@PathVariable UUID userId) {
        User user = pollManager.findUserById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        Vote recentVote = pollManager.findRecentVoteByUserId(userId);
        if (recentVote == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(recentVote);
    }

}
