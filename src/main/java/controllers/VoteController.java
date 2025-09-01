package controllers;

import entities.VoteOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoteController {

    private final PollManager pollManager;

    @Autowired
    public VoteController(PollManager pollManager) {
        this.pollManager = pollManager;
    }
}
