package com.example.demo.controllers;

import com.example.demo.entities.Poll;
import com.example.demo.entities.PollUserDTO;
import com.example.demo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Set;

@RestController
public class PollController {

    private final PollManager pollManager;

    @Autowired
    public PollController(PollManager pollManager) {
        this.pollManager = pollManager;
    }

    // CBA
    // worked on test, but cant figure out how it can work on Bruno
    // is it even a feature i want?
//    @GetMapping("/poll")
//    public ResponseEntity<Poll> getPoll(@RequestParam User user) {
//        for (Map.Entry<Poll, User> entry : pollManager.getPollUserMap().entrySet()) {
//            if (entry.getValue().equals(user)) {
//                return ResponseEntity.ok(entry.getKey());
//            }
//        }
//        return ResponseEntity.notFound().build();
//    }

    // GetMapping uses @RequestParam not @RequestBody
    @GetMapping("/polls")
    public ResponseEntity<Set<Poll>> getPolls() {
        return ResponseEntity.ok(pollManager.getPollUserMap().keySet());
    }

//    @PostMapping("/polls")
//    public ResponseEntity<HashMap<Poll, User>> addPoll(@RequestBody PollUserDTO pollUserDTO) {
//        HashMap<Poll, User> map = pollManager.getPollUserMap();
//        map.put(pollUserDTO.getPoll(),pollUserDTO.getUser());
//        return ResponseEntity.ok(map);
//    }

    // this one
    @PostMapping("/polls")
    public ResponseEntity<HashMap<Poll, User>> addPoll(@RequestBody Poll poll) {
        HashMap<Poll, User> map = pollManager.getPollUserMap();
        map.put(poll, poll.getUser());
        return ResponseEntity.ok(map);
    }
}
