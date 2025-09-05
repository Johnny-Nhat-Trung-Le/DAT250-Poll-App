package com.example.demo.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.UUID;

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

    public UUID getId() { return id; }

    public void setId(UUID id) { this.id = id; }

    public String getQuestion() { return question; }

    public void setQuestion(String question) { this.question = question; }

    public Instant getPublishedAt() { return publishedAt; }

    public void setPublishedAt(Instant publishedAt) { this.publishedAt = publishedAt; }

    public Instant getValidUntil() { return validUntil; }

    public void setValidUntil(Instant validUntil) { this.validUntil = validUntil; }

    public ArrayList<VoteOption> getOptions() { return options; }

    public void setOptions(ArrayList<VoteOption> options) { this.options = options; }

    public UUID getUserId() {return userId;}

    public void setUserId(UUID userId) { this.userId = userId; }

}
