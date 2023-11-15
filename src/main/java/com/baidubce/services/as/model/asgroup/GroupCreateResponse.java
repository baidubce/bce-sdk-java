package com.baidubce.services.as.model.asgroup;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * Created by xucongyang on 2018/07/19.
 */
public class GroupCreateResponse extends AbstractBceResponse {

    private String groupId;
    private List<String> orderId;


    public GroupCreateResponse() {

    }
    public GroupCreateResponse(NodesCreateResult nodesCreateResult) {
        groupId = nodesCreateResult.getGroupId();
        orderId = nodesCreateResult.getOrderId();
    }

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

    @Override
    public String toString() {
        return "GroupCreateResponse{" +
                "groupId='" + groupId + '\'' +
                ", orderId=" + orderId +
                '}';
    }
}
