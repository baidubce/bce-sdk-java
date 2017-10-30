package com.baidubce.services.bacnet.model;

import com.baidubce.model.GenericAccountRequest;

import java.util.List;

/**
 * Created by yuanyoujun on 2017/10/19.
 */
public class SubBacnetObjectRequest extends GenericAccountRequest {
    private String destTopic;
    private List<BacnetObjectId> points;

    public String getDestTopic() {
        return destTopic;
    }

    public void setDestTopic(String destTopic) {
        this.destTopic = destTopic;
    }

    public List<BacnetObjectId> getPoints() {
        return points;
    }

    public void setPoints(List<BacnetObjectId> points) {
        this.points = points;
    }
}
