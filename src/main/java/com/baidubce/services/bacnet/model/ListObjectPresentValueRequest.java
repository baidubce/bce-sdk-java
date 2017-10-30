package com.baidubce.services.bacnet.model;

import com.baidubce.model.GenericAccountRequest;

import java.util.List;

/**
 * Created by yuanyoujun on 2017/10/19.
 */
public class ListObjectPresentValueRequest extends GenericAccountRequest {
    private List<BacnetObjectId> points;

    public List<BacnetObjectId> getPoints() {
        return points;
    }

    public void setPoints(List<BacnetObjectId> points) {
        this.points = points;
    }
}
