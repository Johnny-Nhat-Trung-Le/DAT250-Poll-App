package com.example.demo.controllers;

import com.example.demo.entities.Poll;
import com.example.demo.entities.PollManager;
import com.example.demo.entities.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/polls")
public class PollController {

    private final PollManager pollManager;

    @Autowired
    public PollController(PollManager pollManager) {
        this.pollManager = pollManager;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poll> getPollById(@PathVariable UUID id) {
        if (id == null) {
            return ResponseEntity.noContent().build();
        }
        Poll poll = pollManager.findPollById(id);
        if (poll == null) {
            return ResponseEntity.notFound().build();
        } return ResponseEntity.ok(poll);
    }

    @GetMapping
    public ResponseEntity<Collection<Poll>> getPolls(@RequestParam(required = false) UUID userId) {
        if (userId == null) {
            return ResponseEntity.ok(pollManager.getPolls());
        }
        return ResponseEntity.ok(pollManager.findPollsByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<Poll> addPoll(@RequestBody Poll poll) {
        if (poll == null) {
            return ResponseEntity.noContent().build();
        }
        pollManager.addPoll(poll);
        return ResponseEntity.ok(poll);
    }

    @PostMapping("/{pollId}/vote")
    public ResponseEntity<Void> vote(@PathVariable UUID pollId, @RequestBody Vote vote) {
        if (pollId == null) {
            return ResponseEntity.notFound().build();
        }
        if (vote == null || vote.getOption() == null || vote.getUserId() == null) {
            return ResponseEntity.badRequest().build();
        }
        Poll poll = pollManager.findPollById(pollId);
        if (poll == null) {
            return ResponseEntity.notFound().build();
        }
        pollManager.submitVote(poll,vote);
        return ResponseEntity.ok().build();
    }
    
}
