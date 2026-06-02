package com.baidubce.services.localdns.model;

import com.baidubce.common.BaseBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListRecordRequest extends BaseBceRequest {

    /**
     * 查询的域名ID
     */
    private String zoneId;
    /**
     * 标记查询的起始位置
     */
    private String marker;

    /**
     * 查询记录的最大数量，取值范围为1-1000
     */
    private Integer maxKeys;

    /**
     * 主机记录
     */
    private String rr;

    /**
     * rr 的匹配模式,LIKE表示模糊匹配，EXACT表示精确匹配(默认)
     */
    private String searchMode;

    /**
     * 解析记录类型，目前支持A, AAAA,CNAME, TXT, MX, PTR, SRV
     */
    private String type;

    /**
     * 解析记录值，模糊匹配
     */
    private String value;

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public Integer getMaxKeys() {
        return maxKeys;
    }

    public void setMaxKeys(Integer maxKeys) {
        this.maxKeys = maxKeys;
    }

    public String getRr() {
        return rr;
    }

    public void setRr(String rr) {
        this.rr = rr;
    }

    public String getSearchMode() {
        return searchMode;
    }

    public void setSearchMode(String searchMode) {
        this.searchMode = searchMode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ListRecordRequest{" +
                "zoneId='" + zoneId + '\'' +
                ", marker='" + marker + '\'' +
                ", maxKeys=" + maxKeys +
                ", rr='" + rr + '\'' +
                ", searchMode='" + searchMode + '\'' +
                ", type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
