package com.baidubce.services.bacnet.model;

/**
 * Created by yuanyoujun on 2017/10/19.
 */
public class BacnetObjectId {
    private int  instanceNumber;
    private String objectType;
    private int objectInstance;

    public int getInstanceNumber() {
        return instanceNumber;
    }

    public void setInstanceNumber(int instanceNumber) {
        this.instanceNumber = instanceNumber;
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
}
