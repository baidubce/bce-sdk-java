package com.baidubce.services.iothub.model;

import java.util.List;

public class BatchGetMqttClientStatusRequest extends BaseRequest {

    private List<String> clientIdList;

    public List<String> getClientIdList() {
        return clientIdList;
    }

    public void setClientIdList(List<String> clientIdList) {
        this.clientIdList = clientIdList;
    }
}
