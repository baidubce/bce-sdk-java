package com.baidubce.services.bcm.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * The response for query metric data.
 */
public class MetricDataResponse extends AbstractBceResponse {

    private String requestId = "";

    private String code = "";

    private String message = "";

    List<DataPoint> dataPoints;

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

    public List<DataPoint> getDataPoints() {
        return dataPoints;
    }

    public void setDataPoints(List<DataPoint> dataPoints) {
        this.dataPoints = dataPoints;
    }
}
