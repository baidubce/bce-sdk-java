package com.baidubce.services.bmr.model;

import com.baidubce.model.AbstractBceResponse;

public class ScheduleCreateResponse extends AbstractBceResponse {
    private String scheduleId;
    private String code;
    private String msg;

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

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
}
