package com.baidubce.services.bcm.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * List of Metric Data
 */
public class ListMetricDataResponse extends AbstractBceResponse {

    private String requestId;

    private String code = "";

    private String message = "";

    List<ErrorMetricData> errorList;

    List<SuccessMetricData> successList;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ErrorMetricData> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<ErrorMetricData> errorList) {
        this.errorList = errorList;
    }

    public List<SuccessMetricData> getSuccessList() {
        return successList;
    }

    public void setSuccessList(List<SuccessMetricData> successList) {
        this.successList = successList;
    }

}
