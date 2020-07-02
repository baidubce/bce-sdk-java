package com.baidubce.services.bmr.model;

import com.baidubce.model.AbstractBceResponse;

public class ListHistorySchedulePlanResponse extends AbstractBceResponse {
    private String code;
    private String msg;
    private ScheduleHistoryModelsVo historyModels;

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

    public ScheduleHistoryModelsVo getHistoryModels() {
        return historyModels;
    }

    public void setHistoryModels(ScheduleHistoryModelsVo historyModels) {
        this.historyModels = historyModels;
    }
}
