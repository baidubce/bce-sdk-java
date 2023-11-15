package com.baidubce.services.cdn.model.dsa;

import com.baidubce.services.cdn.model.CdnRequest;

/**
 * create by changxing01 on 19/8/28
 */
public class SetDsaRequest extends CdnRequest {
    /**
     * "enable"为开通动态加速服务，"disable"为关闭
     */
    private String action;

    /**
     * @return action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * @param action
     * @return this object
     */
    public SetDsaRequest withAction(String action) {
        this.action = action;
        return this;
    }
}
