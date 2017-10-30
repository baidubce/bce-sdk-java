package com.baidubce.services.bacnet.model;

import com.baidubce.model.GenericAccountRequest;

import java.util.List;

/**
 * Created by yuanyoujun on 2017/10/19.
 */
public class UpdateBacnetObjectPresentValueRequest extends GenericAccountRequest {
    private List<BacnetObjectPresentValue> points;

    public List<BacnetObjectPresentValue> getPoints() {
        return points;
    }

    public void setPoints(List<BacnetObjectPresentValue> points) {
        this.points = points;
    }
}
