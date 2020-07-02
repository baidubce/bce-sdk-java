package com.baidubce.services.bmr.model;

import java.util.List;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class CreateClusterByTemplateRequest extends AbstractBceRequest {

    private String clientToken;

    private String templateId;

    private String adminPassword;

    private String logUri;

    private String securityGroup;

    private List<StepConfig> steps;

    public CreateClusterByTemplateRequest withClientToken(String clientToken) {
        this.setClientToken(clientToken);
        return this;
    }

    public CreateClusterByTemplateRequest withTemplateId(String templateId) {
        this.setTemplateId(templateId);
        return this;
    }

    public CreateClusterByTemplateRequest withAdminPassword(String adminPassword) {
        this.setAdminPassword(adminPassword);
        return this;
    }

    public CreateClusterByTemplateRequest withLogUri(String logUri) {
        this.setLogUri(logUri);
        return this;
    }

    public CreateClusterByTemplateRequest withSecurityGroup(String securityGroup) {
        this.setSecurityGroup(securityGroup);
        return this;
    }

    public CreateClusterByTemplateRequest withSteps(List<StepConfig> steps) {
        this.setSteps(steps);
        return this;
    }

    @Override public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getLogUri() {
        return logUri;
    }

    public void setLogUri(String logUri) {
        this.logUri = logUri;
    }

    public String getSecurityGroup() {
        return securityGroup;
    }

    public void setSecurityGroup(String securityGroup) {
        this.securityGroup = securityGroup;
    }

    public List<StepConfig> getSteps() {
        return steps;
    }

    public void setSteps(List<StepConfig> steps) {
        this.steps = steps;
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }
}
