package com.example.demo.entities;

import java.io.Serializable;
import java.time.Instant;

public class Vote implements Serializable {

    private Instant publishedAt;

    private User user;
    private VoteOption option;

    public Vote() {}

    // vote has a user connected to it, to option they are voting for and time they voted
    public Vote(User user, VoteOption option, Instant publishedAt) {
        this.user = user;
        this.option = option;
        this.publishedAt = publishedAt;
    }

    public Instant getPublishedAt() { return publishedAt; }

    public void setPublishedAt(Instant publishedAt) { this.publishedAt = publishedAt; }

    // make getters and setters

}
