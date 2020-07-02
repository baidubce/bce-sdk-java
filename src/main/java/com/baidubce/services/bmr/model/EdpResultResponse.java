package com.baidubce.services.bmr.model;

import com.baidubce.model.AbstractBceResponse;

public class EdpResultResponse<T> extends AbstractBceResponse {
    private Boolean success = true;
    private int status = 200;
    private T result;

    public EdpResultResponse() {
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public int getStatus() {
        return this.status;
    }

    public T getResult() {
        return this.result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public EdpResultResponse<T> withResult(T result) {
        this.result = result;
        return this;
    }

    public String toString() {
        return "EdpResultResponse{success=" + this.success + ", status=" + this.status + ", result=" + this.result
                + '}';
    }
}
