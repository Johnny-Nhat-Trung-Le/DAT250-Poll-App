package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PollController {

    private final PollManager pollManager;

    @Autowired
    public PollController(PollManager pollManager) {
        this.pollManager = pollManager;
    }

}
