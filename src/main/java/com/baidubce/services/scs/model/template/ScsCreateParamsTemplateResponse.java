package com.baidubce.services.scs.model.template;

import com.baidubce.model.AbstractBceResponse;

public class ScsCreateParamsTemplateResponse extends AbstractBceResponse {
    private Integer templateId;
    private String templateShowId;

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getTemplateShowId() {
        return templateShowId;
    }

    public void setTemplateShowId(String templateShowId) {
        this.templateShowId = templateShowId;
    }
}
