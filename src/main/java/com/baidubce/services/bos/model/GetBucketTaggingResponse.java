package com.baidubce.services.bos.model;

import java.util.List;

/**
 * GetBucketTaggingResponse class
 */
public class GetBucketTaggingResponse extends BosResponse {
    private List<BucketTag> tag;

    public List<BucketTag> getTag() {
        return tag;
    }

    public void setTag(List<BucketTag> tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "GetBucketTaggingResponse{" +
                "tag=" + tag +
                '}';
    }
}
