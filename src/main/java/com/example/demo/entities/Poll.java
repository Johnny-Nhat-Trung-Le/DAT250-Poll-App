package com.example.demo.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class Poll implements Serializable {

    private String question;
    private Instant publishedAt;
    private Instant validUntil;

    // map, voteoption, votecount?
    private Set<VoteOption> options;
    private User owner;

    //temp for easy testing, remove later
    private int i;

    public Poll() {}

    //temp for easy testing, remove later
    public Poll(int i, User owner) {
        this.i = i;
        this.owner = owner;
    }

    // id?
    public Poll(String question, Set<VoteOption> options, Instant publishedAt, Instant validUntil, User owner) {
        this.question = question;
        this.options = new HashSet<>();
        this.publishedAt = publishedAt;
        this.validUntil = validUntil;
        this.owner = owner;
    }

    //temp for easy testing, remove later
    public int getI() { return i; }

    public String getQuestion() { return question; }

    public void setQuestion(String question) { this.question = question; }

    public Instant getPublishedAt() { return publishedAt; }

    public void setPublishedAt(Instant publishedAt) { this.publishedAt = publishedAt; }

    public Instant getValidUntil() { return validUntil; }

    public void setValidUntil(Instant validUntil) { this.validUntil = validUntil; }

    public Set<VoteOption> getOptions() { return options; }

    public void setOptions(Set<VoteOption> options) { this.options = options; }

    public User getUser() {return owner;}

}
