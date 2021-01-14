package com.baidubce.services.sms.model.v3;

import com.baidubce.services.sms.model.SmsResponse;

import java.util.List;

public class ListTemplateResponse extends SmsResponse {

    private List<GetTemplateResponse> templates;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * The total count of the querying templates
     * TotalCount is null when byPage of the listTemplateRequest is false.
     */
    private int totalCount;

    public List<GetTemplateResponse> getTemplates() {
        return templates;
    }

    public void setTemplates(List<GetTemplateResponse> templates) {
        this.templates = templates;
    }

    @Override
    public String toString() {
        return "ListTemplateResponse [templateList=" + templates + "]";
    }
}
