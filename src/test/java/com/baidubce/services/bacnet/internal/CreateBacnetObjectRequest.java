package com.baidubce.services.bacnet.internal;

import com.baidubce.model.GenericAccountRequest;

/**
 * Created by yuanyoujun on 2017/10/19.
 */
public class CreateBacnetObjectRequest extends GenericAccountRequest {
    private String name;
    private int objectType;
    private int objectInstance;

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
}
