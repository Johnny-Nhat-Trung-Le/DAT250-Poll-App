package com.example.demo.entities;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class Vote implements Serializable {

    private UUID userId;
    private VoteOption option;
    private Instant publishedAt;

    public Vote(UUID userId, VoteOption option) {
        this.userId = userId;
        this.option = option;
        this.publishedAt = Instant.now();
    }

}
