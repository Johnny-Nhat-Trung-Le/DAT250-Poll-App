package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID user_id;

    private UUID id = UUID.randomUUID();

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Poll> created;

    private String username;
    private String email;

    public User() {}

    public User(String username, String email) {
        this.username = username;
        this.email = email;
        this.created = new LinkedHashSet<>();
    }

    public Poll createPoll(String question) {
        Poll newPoll = new Poll(question, new ArrayList<>(), null, null, this.id);
        newPoll.setCreatedBy(this);
        return newPoll;
    }

    public Vote voteFor(VoteOption option) {
        return new Vote(this.id, option);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof User user))  { return false; }
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
