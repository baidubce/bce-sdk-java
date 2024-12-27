package com.baidubce.services.vod.v2.model;

public class ComposeTaskInfo {

    private String status;
    private String errMsg;
    private ComposeTaskOutputInfo output;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public ComposeTaskOutputInfo getOutput() {
        return output;
    }

    public void setOutput(ComposeTaskOutputInfo output) {
        this.output = output;
    }

}
