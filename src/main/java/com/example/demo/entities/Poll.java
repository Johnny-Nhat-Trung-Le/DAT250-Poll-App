package com.example.demo.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Poll implements Serializable {

    private UUID id;

    private String question;
    private Instant publishedAt;
    private Instant validUntil;
    private List<VoteOption> options;
    private final HashMap<VoteOption, Long> voteCount;
    private UUID userId;

    public Poll(String question, List<VoteOption> options, Instant publishedAt, Instant validUntil, UUID userId) {
        this.id = UUID.randomUUID();
        this.question = question;
        this.options = options;
        this.publishedAt = publishedAt;
        this.validUntil = validUntil;
        this.userId = userId;

        this.voteCount = new HashMap<>();
        for (VoteOption voteOption : options) {
            voteCount.put(voteOption, 0L);
        }
    }

    public UUID getId() { return id; }

    public void setId(UUID id) { this.id = id; }

    public String getQuestion() { return question; }

    public void setQuestion(String question) { this.question = question; }

    public Instant getPublishedAt() { return publishedAt; }

    public void setPublishedAt(Instant publishedAt) { this.publishedAt = publishedAt; }

    public Instant getValidUntil() { return validUntil; }

    public void setValidUntil(Instant validUntil) { this.validUntil = validUntil; }

    public List<VoteOption> getOptions() { return options; }

    public void setOptions(List<VoteOption> options) { this.options = options; }

    public HashMap<VoteOption, Long> getVoteCount() { return voteCount; }

    public UUID getUserId() {return userId;}

    public void setUserId(UUID userId) { this.userId = userId; }

}
