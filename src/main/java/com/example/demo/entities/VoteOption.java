package com.example.demo.entities;

import java.io.Serializable;

public class VoteOption implements Serializable {

    private String option;
    private int presentationOrder;
    private Long voteCount;

    public VoteOption() {}

    public VoteOption(String option, int presentationOrder, Long voteCount) {
        this.option = option;
        this.presentationOrder = presentationOrder;
        this.voteCount = 0L;
    }

    public String getOption() { return option; }

    public void setOption(String option) { this.option = option; }

    public int getPresentationOrder() { return presentationOrder; }

    public void setPresentationOrder(int presentationOrder) { this.presentationOrder = presentationOrder; }

    public Long getVoteCount() { return voteCount; }

    public void setVoteCount(Long voteCount) { this.voteCount = voteCount; }

}
