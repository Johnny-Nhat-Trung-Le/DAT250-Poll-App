package com.example.demo.controllers;

import com.example.demo.entities.Poll;
import com.example.demo.entities.PollManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/polls")
public class PollController {

    private final PollManager pollManager;

    @Autowired
    public PollController(PollManager pollManager) {
        this.pollManager = pollManager;
    }

    @GetMapping("/polls/{id}")
    public ResponseEntity<Poll> getPollById(@PathVariable UUID id) {
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
        pollManager.addPoll(poll);
        return ResponseEntity.ok(poll);
    }

    @DeleteMapping("/polls/delete/{id}")
    public ResponseEntity<Void> deletePoll(@PathVariable UUID id) {
        boolean deleted = pollManager.deletePoll(id);
        if (!deleted) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Poll ID " + id + " does not exists");
        }
        return ResponseEntity.noContent().build();
    }

//    @PostMapping("/polls/vote")
//    public

    //@PutMapping to update stuff
}
