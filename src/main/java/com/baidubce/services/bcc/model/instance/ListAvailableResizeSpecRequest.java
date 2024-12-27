package com.baidubce.services.bcc.model.instance;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;
import java.util.List;

@Data
public class ListAvailableResizeSpecRequest extends AbstractBceRequest {
    private String spec;
    private String specId;
    private String zone;
    private List<String> instanceIdList;

    public ListAvailableResizeSpecRequest withSpec(String spec) {
        this.spec = spec;
        return this;
    }

    public ListAvailableResizeSpecRequest withSpecId(String specId) {
        this.specId = specId;
        return this;
    }

    public ListAvailableResizeSpecRequest withZone(String logicalZone) {
        this.zone = logicalZone;
        return this;
    }

    public ListAvailableResizeSpecRequest withInstanceIdList(List<String> instanceIdList) {
        this.instanceIdList = instanceIdList;
        return this;
    }
    @Override
    public ListAvailableResizeSpecRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
