package com.baidubce.services.bacnet.model;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by yuanyoujun on 2017/10/19.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListObjectPresentValueResponse extends AbstractBceResponse {
    private List<BacnetObject> points;

    public List<BacnetObject> getPoints() {
        return points;
    }

    public void setPoints(List<BacnetObject> points) {
        this.points = points;
    }
}
