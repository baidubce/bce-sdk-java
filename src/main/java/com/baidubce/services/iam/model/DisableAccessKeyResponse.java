package com.baidubce.services.iam.model;

import com.baidubce.common.BaseBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * Created by linxiao02 on 2020/6/22
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DisableAccessKeyResponse extends BaseBceResponse {
    /**
     * ak
     */
    private String id;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * description
     */
    private String description;

    /**
     * enabled
     */
    private Boolean enabled;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public String toString() {
        return "DisableAccessKeyResponse{"
                + "id=" + id + "\n"
                + "createTime=" + createTime + "\n"
                + "description=" + description + "\n"
                + "enabled=" + enabled + "\n"
                + "}";
    }
}
