package com.example.demo.entities;

import java.io.Serializable;

public class VoteOption implements Serializable {

    private String option;
    private int presentationOrder;

    public VoteOption() {}

    // int? Long? votecount?
    public VoteOption(String option, int presentationOrder) {
        this.option = option;
        this.presentationOrder = presentationOrder;
        // this.voteCount = 0; , and so on increment for each vote casted on this option with a method
    }

    // make getters and setters for everything, essentially

    // evt. public void incrementVoteCount() { voteCount += 1; }

    public String getOption() { return option; }

    public void setOption(String option) { this.option = option; }

    public int getPresentationOrder() { return presentationOrder; }

    public void setPresentationOrder(int presentationOrder) { this.presentationOrder = presentationOrder; }

}
