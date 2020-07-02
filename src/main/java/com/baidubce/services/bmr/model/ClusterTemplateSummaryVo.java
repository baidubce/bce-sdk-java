package com.baidubce.services.bmr.model;

public class ClusterTemplateSummaryVo extends ClusterTemplateSummary {
    private String imageDescription;
    private boolean isCopyable;
    private boolean abandoned;

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }

    public boolean isCopyable() {
        return isCopyable;
    }

    public void setCopyable(boolean copyable) {
        isCopyable = copyable;
    }

    public boolean isAbandoned() {
        return abandoned;
    }

    public void setAbandoned(boolean abandoned) {
        this.abandoned = abandoned;
    }
}
