package com.baidubce.services.iotsmsreceiver.model;

import com.baidubce.model.GenericAccountRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by yuanyoujun on 2017/8/13.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateIotSmsReceiverRequest extends GenericAccountRequest {
    private String name;
    private String signature;
    private String template;
    private String receivers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getReceivers() {
        return receivers;
    }

    public void setReceivers(String receivers) {
        this.receivers = receivers;
    }
}
