package com.example.demo.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

public class Vote implements Serializable {

    private Instant publishedAt;

    // change user to UUID
    private UUID userId;
    private VoteOption option;

    public Vote() {}

    public Vote(UUID userId, VoteOption option) {
        this.userId = userId;
        this.option = option;
        this.publishedAt = Instant.now();
    }

    public UUID getUserId() { return userId; }

    public void setUserId(UUID userId) { this.userId = userId; }

    public VoteOption getOption() { return option; }

    public void setOption(VoteOption option) { this.option = option; }

    public Instant getPublishedAt() { return publishedAt; }

    public void setPublishedAt(Instant publishedAt) { this.publishedAt = publishedAt; }

}
