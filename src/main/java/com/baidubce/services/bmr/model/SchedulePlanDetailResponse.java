package com.baidubce.services.bmr.model;

import com.baidubce.model.AbstractBceResponse;

public class SchedulePlanDetailResponse extends AbstractBceResponse {
    private String code;
    private String msg;
    private SchedulePlanModelVo ctx;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public SchedulePlanModelVo getCtx() {
        return ctx;
    }

    public void setCtx(SchedulePlanModelVo ctx) {
        this.ctx = ctx;
    }
}
