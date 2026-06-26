package com.baidubce.services.scs.model.instance;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.scs.model.ScsTag;

import java.util.List;

/**
 * Request for scs instance action APIs.
 */
public class ScsInstanceChangeTagRequest extends AbstractBceRequest {

    private String instanceId;
    private List<ScsTag> changeTags;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public List<ScsTag> getChangeTags() {
        return changeTags;
    }

    public void setChangeTags(List<ScsTag> changeTags) {
        this.changeTags = changeTags;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
