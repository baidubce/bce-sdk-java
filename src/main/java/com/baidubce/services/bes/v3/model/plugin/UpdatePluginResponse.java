package com.baidubce.services.bes.v3.model.plugin;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

@Data
public class UpdatePluginResponse extends AbstractBceResponse {
    private String clusterId;
    private String clusterName;
    private String actionId;
    private String orderId;
}
