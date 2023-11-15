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
import com.baidubce.services.cdn.model.GetStatMetricMapping;
import com.baidubce.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The params of metric query
 * 
 */
public class GetStatMetricRequest extends CdnRequest {

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

    /**
     * 客户端访问分布查询平均速率扩展
     * 查询的省份全拼，默认为空，查询全国数据。
     */
    private String prov;

    /**
     * 客户端访问分布查询平均速率扩展
     *
     * 查询的运营商代码，默认为空，查询所有运营商数据
     */
    private String isp;

    /**
     * pv/qps查询扩展
     * 查询流量、带宽扩展
     *
     * 查询边缘节点或者中心节点pv。可填写"all"或"edge"或者"internal"，默认为“all”
     */
    private String level;

    /**
     * pv/qps https协议查询扩展
     *
     * 查询全部pv。可填写"https"或"http"或者"all"，默认为“all”
     */
    private String protocol;

    /**
     * 查询指定http状态码的记录，默认值： ""
     */
    private Integer extra;

    /**
     * 值为pv或者flow，指定按pv排序还是按flow排序，默认按pv排序
     */
    private String sortKey;

    /**
     *
     * @param metric
     * @return
     */
    public GetStatMetricRequest withMetric(String metric) {
        this.metric = metric;
        return this;
    }

    /**
     *
     * @param sortKey
     * @return
     */
    public GetStatMetricRequest withSortKey(String sortKey) {
        this.sortKey = sortKey;
        return this;
    }

    /**
     *
     * @param startTime
     * @return
     */
    public GetStatMetricRequest withStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     *
     * @param startTime
     * @return
     */
    public GetStatMetricRequest withDateStartTime(Date startTime) {
        this.startTime = DateUtils.formatAlternateIso8601Date(startTime);
        return this;
    }

    /**
     *
     * @param endTime
     * @return
     */
    public GetStatMetricRequest withEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    /**
     *
     * @param endTime
     * @return
     */
    public GetStatMetricRequest withDateEndTime(Date endTime) {
        this.endTime = DateUtils.formatAlternateIso8601Date(endTime);
        return this;
    }

    /**
     *
     * @param period
     * @return
     */
    public GetStatMetricRequest withPeriod(Integer period) {
        this.period = period;
        return this;
    }

    /**
     *
     * @param keyType
     * @return
     */
    public GetStatMetricRequest withKeyType(Integer keyType) {
        this.keyType = keyType;
        return this;
    }

    /**
     *
     * @param key
     * @return
     */
    public GetStatMetricRequest withKey(List<String> key) {
        this.key = key;
        return this;
    }

    /**
     *
     * @param key
     * @return
     */
    public GetStatMetricRequest withKey(String key) {
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
    public GetStatMetricRequest withGroupBy(String groupBy) {
        this.groupBy = groupBy;
        return this;
    }

    /**
     * 
     * @param prov
     * @return
     */
    public GetStatMetricRequest withProv(String prov) {
        this.prov = GetStatMetricMapping.PROVINCE_MAP.inverse().get(prov);
        this.prov = StringUtils.isEmpty(this.prov) ? prov : this.prov;
        return this;
    }

    /**
     * 
     * @param isp
     * @return
     */
    public GetStatMetricRequest withIsp(String isp) {
        this.isp = GetStatMetricMapping.ISP_MAP.inverse().get(isp);
        this.isp = StringUtils.isEmpty(this.isp) ? isp : this.isp;
        return this;
    }

    /**
     * 
     * @param level
     * @return
     */
    public GetStatMetricRequest withLevel(String level) {
        this.level = level;
        return this;
    }

    /**
     * 
     * @param protocol
     * @return
     */
    public GetStatMetricRequest withProtocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    /**
     * 
     * @param extra
     * @return
     */
    public GetStatMetricRequest withExtra(Integer extra) {
        this.extra = extra;
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

    /**
     * 
     * @return
     */
    public List<String> getKey() {
        return key;
    }

    /**
     * 
     * @param key
     */
    public void setKey(List<String> key) {
        this.key = key;
    }

    /**
     * 
     * @return
     */
    public String getGroupBy() {
        return groupBy;
    }

    /**
     * 
     * @param groupBy
     */
    public void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }

    /**
     * 
     * @return
     */
    public String getProv() {
        return prov;
    }

    /**
     * 
     * @param prov
     */
    public void setProv(String prov) {
        this.prov = prov;
    }

    /**
     * 
     * @return
     */
    public String getIsp() {
        return isp;
    }

    /**
     * 
     * @param isp
     */
    public void setIsp(String isp) {
        this.isp = isp;
    }

    /**
     * 
     * @return
     */
    public String getLevel() {
        return level;
    }

    /**
     * 
     * @param level
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * 
     * @return
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * 
     * @param protocol
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public Integer getExtra() {
        return extra;
    }

    public void setExtra(Integer extra) {
        this.extra = extra;
    }

    public String getSortKey() {
        return sortKey;
    }

    public void setSortKey(String sortKey) {
        this.sortKey = sortKey;
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
