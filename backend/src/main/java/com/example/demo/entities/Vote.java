package com.example.demo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Vote implements Serializable {

    private UUID userId;
    private VoteOption option;
    private Instant publishedAt;

    public Vote(UUID userId, VoteOption option) {
        this.userId = userId;
        this.option = option;
        this.publishedAt = Instant.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vote vote)) return false;
        return Objects.equals(userId, vote.userId) &&
                Objects.equals(option,vote.option);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, option);
    }

}
