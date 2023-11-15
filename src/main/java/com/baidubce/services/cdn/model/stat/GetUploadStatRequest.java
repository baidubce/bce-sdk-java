package com.baidubce.services.cdn.model.stat;

import com.baidubce.services.cdn.model.CdnRequest;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GetUploadStatRequest extends CdnRequest {
    private String type;

    private String startTime;
    private String endTime;

    /**
     * 查询结果的粒度，单位秒，可选值为60,300,3600,86400；
     * 默认为300，uv 默认3600（
     * 选60s的时间粒度时建议startTime和endTime区间跨度建议选择0.5到1h，否则可能会因为数据量太大无法正常返回）
     */
    private Integer period = 300;

    private List<String> domains;

    private String level;

    /**
     * 指定查询流量or带宽数据，true时查询带宽数据，false时查询流量数据，默认值为false
     */
    @JsonProperty("isBandwidth")
    private Boolean isBandwidth = false;

    /**
     * dataType参数指定查询的数据类型，
     * "all"表示总数据（dsa+static），"dsa"表示动态数据，"static"表示静态数据。默认值为 "all"
     */
    private String dataType = "all";

    public GetUploadStatRequest() {
    }

    public GetUploadStatRequest withType(String type) {
        this.type = type;
        return this;
    }

    public GetUploadStatRequest withStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public GetUploadStatRequest withEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public GetUploadStatRequest withDataType(String dataType) {
        this.dataType = dataType;
        return this;
    }

    public GetUploadStatRequest withLevel(String level) {
        this.level = level;
        return this;
    }

    public GetUploadStatRequest withDomains(List<String> domains) {
        this.domains = domains;
        return this;
    }

    public GetUploadStatRequest withBandwidth(Boolean bandwidth) {
        isBandwidth = bandwidth;
        return this;
    }

    public GetUploadStatRequest withPeriod(Integer period) {
        this.period = period;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(List<String> domains) {
        this.domains = domains;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Boolean getBandwidth() {
        return isBandwidth;
    }

    public void setBandwidth(Boolean bandwidth) {
        isBandwidth = bandwidth;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
