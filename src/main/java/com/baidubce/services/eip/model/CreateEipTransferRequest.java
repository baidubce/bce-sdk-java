package com.baidubce.services.eip.model;

import com.baidubce.common.BaseBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateEipTransferRequest extends BaseBceRequest {

    /**
    * clientToken
    */
    @JsonIgnore
    private String clientToken;

    /**
    * 转移资源类型。eip
    */
    private String transferType;

    /**
    * 转移资源短ID列表,单次最多转移30个。
    */
    private List<String> transferResourceList;

    /**
    * 目标账号ID。
    */
    private String toUserId;


    public String getClientToken() {
        return clientToken;
    }

    public CreateEipTransferRequest setClientToken(String clientToken) {
        this.clientToken = clientToken;
        return this;
    }

    public String getTransferType() {
        return transferType;
    }

    public CreateEipTransferRequest setTransferType(String transferType) {
        this.transferType = transferType;
        return this;
    }

    public List<String> getTransferResourceList() {
        return transferResourceList;
    }

    public CreateEipTransferRequest setTransferResourceList(List<String> transferResourceList) {
        this.transferResourceList = transferResourceList;
        return this;
    }

    public String getToUserId() {
        return toUserId;
    }

    public CreateEipTransferRequest setToUserId(String toUserId) {
        this.toUserId = toUserId;
        return this;
    }


}
