package com.baidubce.services.bacnet.model;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by yuanyoujun on 2017/10/18.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BacnetGatewayCredential extends AbstractBceResponse {
    private String endpoint;
    private String username;
    private String password;
    private String cmdTopic;
    private String dataTopic;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCmdTopic() {
        return cmdTopic;
    }

    public void setCmdTopic(String cmdTopic) {
        this.cmdTopic = cmdTopic;
    }

    public String getDataTopic() {
        return dataTopic;
    }

    public void setDataTopic(String dataTopic) {
        this.dataTopic = dataTopic;
    }
}
