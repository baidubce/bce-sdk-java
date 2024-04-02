package com.baidubce.services.bacnet.internal;

import java.util.Date;

/**
 * Created by yuanyoujun on 2017/10/19.
 */
public class BacnetObjectState {
    private String name;
    private int objectType;
    private int objectInstance;
    private double presentValue;
    private Date updateTime;
    private boolean createNew;

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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isCreateNew() {
        return createNew;
    }

    public void setCreateNew(boolean createNew) {
        this.createNew = createNew;
    }
}
