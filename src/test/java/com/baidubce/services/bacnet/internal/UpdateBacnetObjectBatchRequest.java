package com.baidubce.services.bacnet.internal;

import com.baidubce.model.GenericAccountRequest;

import java.util.List;

/**
 * Created by yuanyoujun on 2017/10/19.
 */
public class UpdateBacnetObjectBatchRequest extends GenericAccountRequest {
    private List<BacnetObjectState> points;

    public List<BacnetObjectState> getPoints() {
        return points;
    }

    public void setPoints(List<BacnetObjectState> points) {
        this.points = points;
    }
}
