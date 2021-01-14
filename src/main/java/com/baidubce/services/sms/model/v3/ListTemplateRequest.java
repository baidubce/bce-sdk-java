package com.baidubce.services.sms.model.v3;

import com.baidubce.services.sms.model.SmsRequest;

public class ListTemplateRequest extends SmsRequest {
    /**
     * The name of the template
     */
    private String name;

    /**
     * The content of the template
     */
    private String content;

    /**
     * The unique id of the template
     */
    private String templateId;

    /**
     * The sms type of the template content <br/>
     * All the smsTypes can be obtained from cloud.baidu.com
     */
    private String smsType;

    /**
     * The status of the template
     */
    private String status;

    /**
     * The countryType indicates the countries or regions in which the template can be used.<br/>
     * The value of countryType could be DOMESTIC or INTERNATIONAL or GLOBAL.<br/>
     * DOMESTIC means the template can only be used in Mainland China;<br/>
     * INTERNATIONAL means the template can only be used out of Mainland China;<br/>
     * GLOBAL means the template can only be used all over the world.
     */
    private String countryType;

    /**
     * The current page number
     */
    private Integer pageNo;

    /**
     * The current page size, range from 1 to 999
     */
    private Integer pageSize;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getSmsType() {
        return smsType;
    }

    public void setSmsType(String smsType) {
        this.smsType = smsType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCountryType() {
        return countryType;
    }

    public void setCountryType(String countryType) {
        this.countryType = countryType;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
