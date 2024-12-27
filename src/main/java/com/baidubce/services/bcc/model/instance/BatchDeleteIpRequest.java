package com.baidubce.services.bcc.model.instance;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * @Author: lilu24
 * @Date: 2024-06-12
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BatchDeleteIpRequest extends AbstractBceRequest {


    private String instanceId;
    private List<String> privateIps;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
