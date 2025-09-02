package com.example.demo.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;


public class Poll implements Serializable {

    private String question;
    private Instant publishedAt;
    private Instant validUntil;

    private Set<VoteOption> options;

    public Poll() {
    }

    public Poll(String question, Set<VoteOption> options, Instant publishedAt, Instant validUntil) {
        this.question = question;
        this.options = options;
        this.publishedAt = publishedAt;
        this.validUntil = validUntil;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Instant getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Instant getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Instant validUntil) {
        this.validUntil = validUntil;
    }

    public Set<VoteOption> getOptions() { return options; }

    public void setOptions(Set<VoteOption> options) { this.options = options; }
}
