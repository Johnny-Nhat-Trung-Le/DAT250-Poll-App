package com.example.demo.entities;

import java.io.Serializable;

public class VoteOption implements Serializable {

    private int id;
    private String option;

    public VoteOption(int id, String option) {
        this.id = id;
        this.option = option;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getOption() { return option; }

    public void setOption(String option) { this.option = option; }

}
