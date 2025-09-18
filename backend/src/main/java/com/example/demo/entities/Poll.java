package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.sql.SQLType;
import java.time.Instant;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "polls")
public class Poll implements Serializable {

    private String question;
    private Instant publishedAt;
    private Instant validUntil;
    private UUID userId;

    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VoteOption> options;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID poll_id;

    private UUID id = UUID.randomUUID();

    @ElementCollection
    @CollectionTable(name = "vote_count", joinColumns = @JoinColumn(name = "poll_id"))
    @MapKeyColumn(name = "option_id")
    @Column(name = "count")
    private Map<Integer, Long> voteCount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User createdBy;

    public Poll() {}

    public Poll(String question, ArrayList<VoteOption> options, Instant publishedAt, Instant validUntil, UUID userId) {
        this.question = question;
        this.options = new ArrayList<>(options.stream().sorted(Comparator.comparing(VoteOption::getPresentationOrder)).toList());
        this.publishedAt = publishedAt;
        this.validUntil = validUntil;
        this.userId = userId;

        this.voteCount = new HashMap<>();
        for (VoteOption voteOption : options) {
            voteOption.setPoll(this);
            voteCount.put(voteOption.getPresentationOrder(), 0L);
        }
    }

    public VoteOption addVoteOption(String caption) {
        if (options == null) {
            options = new ArrayList<>();
        }
        int nextOrderInt = options.isEmpty() ? 0 : options.getLast().getPresentationOrder() + 1;
        VoteOption newVoteOption = new VoteOption(nextOrderInt, caption);
        newVoteOption.setPoll(this);
        options.add(newVoteOption);
        return newVoteOption;
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
