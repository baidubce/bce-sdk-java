/*
 * Copyright 2016-2019 Baidu, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.baidubce.services.cdn.model.stat;

import com.baidubce.services.cdn.model.CdnRequest;
import com.baidubce.util.DateUtils;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * The params of metric query
 * 
 */
public class GetXcdnStatMetricRequest extends CdnRequest {

    /**
     * 指定avg_speed，avg_speed_region，pv，pv_region等
     * 必选
     */
    private String metric;

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
    private Integer keyType = 0;

    /**
     * 名或用户Id或Tag
     */
    private List<String> keys;

    private Boolean groupByKey;

    private String productType;

    /**
     *
     * @param metric
     * @return
     */
    public GetXcdnStatMetricRequest withMetric(String metric) {
        this.metric = metric;
        return this;
    }

    public GetXcdnStatMetricRequest withStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     *
     * @param startTime
     * @return
     */
    public GetXcdnStatMetricRequest withStartTime(Date startTime) {
        this.startTime = DateUtils.formatAlternateIso8601Date(startTime);
        return this;
    }

    /**
     *
     * @param endTime
     * @return
     */
    public GetXcdnStatMetricRequest withEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    /**
     *
     * @param endTime
     * @return
     */
    public GetXcdnStatMetricRequest withEndTime(Date endTime) {
        this.endTime = DateUtils.formatAlternateIso8601Date(endTime);
        return this;
    }

    /**
     *
     * @param period
     * @return
     */
    public GetXcdnStatMetricRequest withPeriod(Integer period) {
        this.period = period;
        return this;
    }

    /**
     *
     * @param keyType
     * @return
     */
    public GetXcdnStatMetricRequest withKeyType(Integer keyType) {
        this.keyType = keyType;
        return this;
    }

    /**
     *
     * @param keys
     * @return
     */
    public GetXcdnStatMetricRequest withKeys(List<String> keys) {
        this.keys = keys;
        return this;
    }

    /**
     *
     * @param keys
     * @return
     */
    public GetXcdnStatMetricRequest withKeys(String keys) {
        if (null == this.keys) {
            this.keys = new ArrayList<String>();
        }
        this.keys.add(keys);
        return this;
    }

    /**
     *
     * @param groupByKey
     * @return
     */
    public GetXcdnStatMetricRequest withGroupByKey(Boolean groupByKey) {
        this.groupByKey = groupByKey;
        return this;
    }

    /**
     * 
     * @return
     */
    public String getMetric() {
        return metric;
    }

    /**
     * 
     * @param metric
     */
    public void setMetric(String metric) {
        this.metric = metric;
    }

    /**
     * 
     * @return
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * 
     * @param startTime
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * 
     * @return
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * 
     * @param endTime
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * 
     * @return
     */
    public Integer getPeriod() {
        return period;
    }

    /**
     * 
     * @param period
     */
    public void setPeriod(Integer period) {
        this.period = period;
    }

    /**
     * 
     * @return
     */
    public Integer getKeyType() {
        return keyType;
    }

    /**
     * 
     * @param keyType
     */
    public void setKeyType(Integer keyType) {
        this.keyType = keyType;
    }

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    public Boolean getGroupByKey() {
        return groupByKey;
    }

    public void setGroupByKey(Boolean groupByKey) {
        this.groupByKey = groupByKey;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    /**
     * Parse this instance to map . When additional parameters need to be added
     * 
     * @return
     * @throws Exception
     */
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Field[] declaredFields = this.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(this));
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
