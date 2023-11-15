package com.baidubce.services.as.model.asgroup;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by xucongyang on 2018/06/25.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BlbInfo {
    private String blbId;
    private String blbName;
    private String blbPublicIp;
    private String blbType;

    public String getBlbId() {
        return blbId;
    }

    public void setBlbId(String blbId) {
        this.blbId = blbId;
    }

    public String getBlbName() {
        return blbName;
    }

    public void setBlbName(String blbName) {
        this.blbName = blbName;
    }

    public String getBlbType() {
        return blbType;
    }

    public void setBlbType(String blbType) {
        this.blbType = blbType;
    }

    public String getBlbPublicIp() {
        return blbPublicIp;
    }

    public void setBlbPublicIp(String blbPublicIp) {
        this.blbPublicIp = blbPublicIp;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BlbInfo{");
        sb.append("blbId='").append(blbId).append('\'');
        sb.append(", blbName='").append(blbName).append('\'');
        sb.append(", blbType='").append(blbType).append('\'');
        sb.append(", blbPublicIp='").append(blbPublicIp).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
