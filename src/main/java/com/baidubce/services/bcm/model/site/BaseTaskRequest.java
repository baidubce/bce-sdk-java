package com.baidubce.services.bcm.model.site;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

import java.util.List;

/**
 * create by pangyangyang on 2023/12/13
 */
@Data
public class BaseTaskRequest extends AbstractBceRequest {

    private String userId;
    private String taskId;
    private String scope = "BCM_SITE";
    private String taskName;
    private String address;
    private String type;
    private Integer cycle;
    private String idc;
    private List<IDC> idcObjs;
    private int timeout;
    private String confResource;
    private Boolean advanceConfig;
    private String ipType = "ipv4";

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
