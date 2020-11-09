package com.baidubce.services.bmr.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class TemplateIdRequest extends AbstractBceRequest {
    private String templateId;
    private String adminPassword;

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public TemplateIdRequest withTemplateId(String templateId) {
        this.setTemplateId(templateId);
        return this;
    }

    public TemplateIdRequest withAdminPassword(String adminPassword) {
        this.setAdminPassword(adminPassword);
        return this;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    @Override public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
