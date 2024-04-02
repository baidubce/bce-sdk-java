package com.baidubce.services.bacnet.internal;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * Created by yuanyoujun on 2017/10/19.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BacnetObjectInternal extends AbstractBceResponse {
    private String name;
    private int objectType;
    private int objectInstance;
    private double presentValue;
    private String destTopics;
    private Date updateTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getObjectType() {
        return objectType;
    }

    public void setObjectType(int objectType) {
        this.objectType = objectType;
    }

    public int getObjectInstance() {
        return objectInstance;
    }

    public void setObjectInstance(int objectInstance) {
        this.objectInstance = objectInstance;
    }

    public double getPresentValue() {
        return presentValue;
    }

    public void setPresentValue(double presentValue) {
        this.presentValue = presentValue;
    }

    public String getDestTopics() {
        return destTopics;
    }

    public void setDestTopics(String destTopics) {
        this.destTopics = destTopics;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
