package com.baidubce.services.cdn.model.stat;

import com.baidubce.services.cdn.model.CdnRequest;
import com.baidubce.services.cdn.model.GetStatMetricMapping;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class GetIpv6StatRequest extends CdnRequest {

    /**
     * stat_type 表查询的数据类型，
     * 值为为 http 时表查询的 http 数据；
     * 值为 https 时表查询的 https 的数据；
     * 值为 all 时表查询的包含http，https的总数据；
     * 值为region时表示查询分省份-运营商的数据；默认查询all
     */
    private String statType;

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
     * 名或用户Id或Tag
     */
    private List<String> keys;

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

    public GetIpv6StatRequest() {
    }

    public GetIpv6StatRequest withStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public GetIpv6StatRequest withEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    /**
     *
     * @param period
     * @return
     */
    public GetIpv6StatRequest withPeriod(Integer period) {
        this.period = period;
        return this;
    }

    /**
     *
     * @return
     */
    public GetIpv6StatRequest withKeys(List<String> keys) {
        this.keys = keys;
        return this;
    }

    /**
     *
     * @return
     */
    public GetIpv6StatRequest withStatType(String statType) {
        this.statType = statType;
        return this;
    }

    /**
     *
     * @param prov
     * @return
     */
    public GetIpv6StatRequest withProv(String prov) {
        this.prov = GetStatMetricMapping.PROVINCE_MAP.inverse().get(prov);
        this.prov = StringUtils.isEmpty(this.prov) ? prov : this.prov;
        return this;
    }

    /**
     *
     * @param isp
     * @return
     */
    public GetIpv6StatRequest withIsp(String isp) {
        this.isp = GetStatMetricMapping.ISP_MAP.inverse().get(isp);
        this.isp = StringUtils.isEmpty(this.isp) ? isp : this.isp;
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

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getStatType() {
        return statType;
    }

    public void setStatType(String statType) {
        this.statType = statType;
    }
}
