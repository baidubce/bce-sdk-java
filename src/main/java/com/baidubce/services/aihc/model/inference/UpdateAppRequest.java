package com.baidubce.services.aihc.model.inference;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateAppRequest extends AbstractBceRequest {

    private String appId;
    private String shortDesc;
    private String appName;
    private String chipType;
    private int insCount;
    private CreateAppRequest.ResPoolConf resPool;
    private CreateAppRequest.StorageConf storage;
    private List<CreateAppRequest.ContainerConf> containers;
    private CreateAppRequest.AccessConf access;
    private CreateAppRequest.LogConf log;
    private CreateAppRequest.DeployConf deploy;
    private CreateAppRequest.Misc misc;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return UpdateAppRequest with credentials.
     */
    public UpdateAppRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
