package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;


@Getter
@Setter
@Entity
@Table(name = "vote_options")
public class VoteOption implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID vote_option_id;

    @ManyToOne
    @JoinColumn(name = "poll_id")
    @JsonBackReference
    private Poll poll;

    private int presentationOrder;
    private String caption;

    public VoteOption() {}

    public VoteOption(int presentationOrder, String caption) {
        this.presentationOrder = presentationOrder;
        this.caption = caption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VoteOption voteOption)) return false;
        return presentationOrder == voteOption.presentationOrder &&
                Objects.equals(caption, voteOption.caption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(presentationOrder, caption);
    }

}
