package com.baidubce.services.bcm.model.site;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

/**
 * create by pangyangyang on 2023/12/13
 */
@Data
public class HttpsTaskRequest extends BaseTaskRequest {

    private String method;
    private String postContent;
    private String cookies;
    private String host;
    private String userAgent;
    private String responseCode;
    private String responseCheck = "";
    private String responseCheckType = "CONTAIN";
    private String responseCheckRange = "BODY";
    private String userName = "";
    private String password = "";

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
