package com.baidubce.services.bacnet.model;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * Created by yuanyoujun on 2017/10/19.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BacnetObject extends AbstractBceResponse {
    private String name;
    private String objectType;
    private int objectInstance;
    private double presentValue;
    private String destTopics;
    private Date updateTime;
    private Date createTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
