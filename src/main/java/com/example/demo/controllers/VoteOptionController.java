package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoteOptionController {

    private final PollManager pollManager;

    @Autowired
    public VoteOptionController(PollManager pollManager) {
        this.pollManager = pollManager;
    }

}
