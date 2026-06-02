package com.baidubce.services.bcc.model.volume;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.util.List;

public class ModifyVolumeDeleteProtectionRequest extends AbstractBceRequest {

    /**
     * 云硬盘ID列表
     */
    private List<String> volumeIds;

    /**
     * 是否开启删除保护
     */
    private Boolean enableDeleteProtection = true;

    public void setVolumeIds(List<String> volumeIds) {
        this.volumeIds = volumeIds;
    }

    public List<String> getVolumeIds() {
        return volumeIds;
    }

    public void setEnableDeleteProtection(Boolean enableDeleteProtection) {
        this.enableDeleteProtection = enableDeleteProtection;
    }

    public Boolean getEnableDeleteProtection() {
        return enableDeleteProtection;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
