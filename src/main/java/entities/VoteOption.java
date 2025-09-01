package entities;

import java.io.Serializable;

public class VoteOption implements Serializable {

    private String option;
    private int presentationOrder;

    public VoteOption() {
    }

    public VoteOption(String option, int presentationOrder) {
        this.option = option;
        this.presentationOrder = presentationOrder;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public int getPresentationOrder() {
        return presentationOrder;
    }

    public void setPresentationOrder(int presentationOrder) {
        this.presentationOrder = presentationOrder;
    }
}
