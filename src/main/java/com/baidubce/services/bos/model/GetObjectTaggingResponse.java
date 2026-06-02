package com.baidubce.services.bos.model;

import java.util.List;

public class GetObjectTaggingResponse extends BosResponse {

    private List<ObjectTag> tagSet;

    public List<ObjectTag> getTagSet() {
        return tagSet;
    }

    public void setTagSet(List<ObjectTag> tagSet) {
        this.tagSet = tagSet;
    }


    @Override
    public String toString() {
        return "GetObjectTaggingResponse{" +
                "tag=" + tagSet +
                '}';
    }
}
