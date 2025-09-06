package com.example.demo.entities;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.Instant;
import java.util.*;

@Getter
@Setter
public class Poll implements Serializable {

    private UUID id;
    private String question;
    private Instant publishedAt;
    private Instant validUntil;
    private ArrayList<VoteOption> options;
    private UUID userId;
    private final HashMap<Integer, Long> voteCount;

    public Poll(String question, ArrayList<VoteOption> options, Instant publishedAt, Instant validUntil, UUID userId) {
        this.id = UUID.randomUUID();
        this.question = question;
        this.options = new ArrayList<>(options.stream().sorted(Comparator.comparing(VoteOption::getId)).toList());
        this.publishedAt = publishedAt;
        this.validUntil = validUntil;
        this.userId = userId;

        this.voteCount = new HashMap<>();
        for (VoteOption voteOption : options) {
            voteCount.put(voteOption.getId(), 0L);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Poll poll)) return false;
        return Objects.equals(id, poll.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
