package com.baidubce.services.bmr.model;

import com.baidubce.model.AbstractBceResponse;

public class ScheduleResultResponse extends AbstractBceResponse {
    private String code;
    private String msg;
    private Boolean success;

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

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
