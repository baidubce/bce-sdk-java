package com.baidubce.services.vodpro.model.adaptor.response;

/**
 * Created on 17/10/18
 *
 * @author liumin08
 */
public class Label {
    private int label;
    private String level;
    private Double rate;

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public String getLevel() {
        return level;
    }

    public Integer getLevelCode() {
        if (this.level.equals(Constants.RESULTLABEL_REVIEW)) {
            return Constants.RESULTLABEL_REVIEW_CODE;
        }
        if (this.level.equals(Constants.RESULTLABEL_NORMAL)) {
            return Constants.RESULTLABEL_NORMAL_CODE;
        }
        if (this.level.equals(Constants.RESULTLABEL_REJECT)) {
            return Constants.RESULTLABEL_REJECT_CODE;
        }
        return Constants.RESULTLABEL_INVALID_CODE;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Label{"
                + "label=" + label
                + ", level=" + level
                + ", rate=" + rate
                + '}';
    }
}
