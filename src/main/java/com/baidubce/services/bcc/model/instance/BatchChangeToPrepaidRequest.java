package com.baidubce.services.bcc.model.instance;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;
import java.util.List;

@Data
public class BatchChangeToPrepaidRequest extends AbstractBceRequest {
    private List<ChangeToPrepaidRequest> config;

    public BatchChangeToPrepaidRequest withConfig(List<ChangeToPrepaidRequest> config){
        this.config = config;
        return this;
    }
    public BatchChangeToPrepaidRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
