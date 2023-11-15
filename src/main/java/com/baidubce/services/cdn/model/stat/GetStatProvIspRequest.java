package com.baidubce.services.cdn.model.stat;

import com.baidubce.services.cdn.model.GetStatMetricMapping;
import org.apache.commons.lang3.StringUtils;

public class GetStatProvIspRequest extends GetStatDefaultRequest {
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

    public GetStatProvIspRequest() {
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

    /**
     *
     * @param prov
     * @return
     */
    public GetStatProvIspRequest withProv(String prov) {
        this.prov = GetStatMetricMapping.PROVINCE_MAP.inverse().get(prov);
        this.prov = StringUtils.isEmpty(this.prov) ? prov : this.prov;
        return this;
    }

    /**
     *
     * @param isp
     * @return
     */
    public GetStatProvIspRequest withIsp(String isp) {
        this.isp = GetStatMetricMapping.ISP_MAP.inverse().get(isp);
        this.isp = StringUtils.isEmpty(this.isp) ? isp : this.isp;
        return this;
    }
}
