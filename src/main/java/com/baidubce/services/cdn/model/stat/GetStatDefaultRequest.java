package com.baidubce.services.cdn.model.stat;

import com.baidubce.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetStatDefaultRequest {
    /**
     * 查询的时间范围结束值，默认为当前时间。时间跨度最长90天 UTC时间
     */
    private String startTime;

    /**
     * 查询的时间范围起始值，默认为endTime前推24小时 UTC时间
     */
    private String endTime;

    /**
     * 查询结果的粒度，单位秒，可选值为60,300,3600,86400；
     * 默认为300，uv 默认3600（
     * 选60s的时间粒度时建议startTime和endTime区间跨度建议选择0.5到1h，否则可能会因为数据量太大无法正常返回）
     */
    private Integer period = 300;

    /**
     * 标识key的内容，0=>域名，1=>用户id，2=>tag，默认0
     */
    @JsonProperty("key_type")
    private Integer keyType = 0;

    /**
     * 名或用户Id或Tag
     */
    private List<String> key;

    /**
     * 返回结果聚合粒度，key => 根据key聚合， 空 => 返回整体结果
     */
    private String groupBy = "key";

    public GetStatDefaultRequest() {
    }

    /**
     *
     * @param startTime
     * @return
     */
    public GetStatDefaultRequest withStartTime(Date startTime) {
        this.startTime = DateUtils.formatAlternateIso8601Date(startTime);
        return this;
    }

    /**
     *
     * @param endTime
     * @return
     */
    public GetStatDefaultRequest withEndTime(Date endTime) {
        this.endTime = DateUtils.formatAlternateIso8601Date(endTime);
        return this;
    }

    /**
     *
     * @param period
     * @return
     */
    public GetStatDefaultRequest withPeriod(Integer period) {
        this.period = period;
        return this;
    }

    /**
     *
     * @param keyType
     * @return
     */
    public GetStatDefaultRequest withKeyType(Integer keyType) {
        this.keyType = keyType;
        return this;
    }

    /**
     *
     * @param key
     * @return
     */
    public GetStatDefaultRequest withKey(List<String> key) {
        this.key = key;
        return this;
    }

    /**
     *
     * @param key
     * @return
     */
    public GetStatDefaultRequest withKey(String key) {
        if (null == this.key) {
            this.key = new ArrayList<String>();
        }
        this.key.add(key);
        return this;
    }

    /**
     *
     * @param groupBy
     * @return
     */
    public GetStatDefaultRequest withGroupBy(String groupBy) {
        this.groupBy = groupBy;
        return this;
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

    public Integer getKeyType() {
        return keyType;
    }

    public void setKeyType(Integer keyType) {
        this.keyType = keyType;
    }

    public List<String> getKey() {
        return key;
    }

    public void setKey(List<String> key) {
        this.key = key;
    }

    public String getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }
}
