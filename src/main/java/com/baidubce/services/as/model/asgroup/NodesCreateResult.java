package com.baidubce.services.as.model.asgroup;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by xucongyang on 2018/06/20.
 */
public class NodesCreateResult {

    private String groupId;
    private List<String> orderId;
    private List<String> instanceId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<String> getOrderId() {
        return orderId;
    }

    public void setOrderId(List<String> orderId) {
        this.orderId = orderId;
    }

    public List<String> getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(List<String> instanceId) {
        this.instanceId = instanceId;
    }

    public NodesCreateResult withGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }
}
