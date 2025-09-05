package com.example.demo.entities;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class VoteOption implements Serializable {

    private int id;
    private String option;

    public VoteOption(int id, String option) {
        this.id = id;
        this.option = option;
    }

}
