package com.baidubce.services.bcm.model.site;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

/**
 * create by pangyangyang on 2023/12/13
 */
@Data
public class FtpTaskRequest extends BaseTaskRequest {

    private int port;
    private boolean anonymousLogin;
    private String userName;
    private String password;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
