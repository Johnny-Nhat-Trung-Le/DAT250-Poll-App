package com.example.demo.entities;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter
public class Poll implements Serializable {

    private UUID id;

    private String question;
    private Instant publishedAt;
    private Instant validUntil;
    private ArrayList<VoteOption> options;
    private UUID userId;

    public Poll(String question, ArrayList<VoteOption> options, Instant publishedAt, Instant validUntil, UUID userId) {
        this.id = UUID.randomUUID();
        this.question = question;
        this.options = options;
        this.publishedAt = publishedAt;
        this.validUntil = validUntil;
        this.userId = userId;
    }

}
