package com.baidubce.services.bmr.model;

import com.baidubce.model.AbstractBceResponse;

/**
 *
 *
 *
 */
public class TemplateClusterResponse extends AbstractBceResponse {

    private String templateId;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }
}
