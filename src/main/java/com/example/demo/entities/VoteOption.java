package com.example.demo.entities;

import java.io.Serializable;

public class VoteOption implements Serializable {

    private int presentationOrder;
    private String option;

    public VoteOption() {}

    public VoteOption(int presentationOrder, String option) {
        this.presentationOrder = presentationOrder;
        this.option = option;
    }

    public int getPresentationOrder() { return presentationOrder; }

    public void setPresentationOrder(int presentationOrder) { this.presentationOrder = presentationOrder; }

    public String getOption() { return option; }

    public void setOption(String option) { this.option = option; }

}
