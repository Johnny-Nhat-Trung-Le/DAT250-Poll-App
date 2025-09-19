package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "votes")
public class Vote implements Serializable {

    @Id
    @GeneratedValue
    private UUID vote_id;

    private UUID id;

    @ManyToOne
    @JoinColumn(name = "vote_option_id")
    private VoteOption votesOn;

    private Instant publishedAt;

    public Vote() {}

    public Vote(UUID id, VoteOption votesOn) {
        this.id = id;
        this.votesOn = votesOn;
        this.publishedAt = Instant.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vote vote)) return false;
        return Objects.equals(id, vote.id) &&
                Objects.equals(votesOn,vote.votesOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, votesOn);
    }

}
