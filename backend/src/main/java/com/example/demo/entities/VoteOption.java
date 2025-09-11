package com.example.demo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class VoteOption implements Serializable {

    private int id;
    private String option;

    public VoteOption(int id, String option) {
        this.id = id;
        this.option = option;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VoteOption voteOption)) return false;
        return id == voteOption.id &&
                Objects.equals(option, voteOption.option);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, option);
    }

}
