package com.baidubce.services.cdn.model.stat;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BillingDetail {

    /**
     * 95带宽值
     */
    @JsonProperty("bill_band")
    private Long billBand;

    /**
     * 该数据点的时间
     */
    @JsonProperty("bill_time")
    private String billTime;

    public BillingDetail() {
    }

    public Long getBillBand() {
        return billBand;
    }

    public void setBillBand(Long billBand) {
        this.billBand = billBand;
    }

    public String getBillTime() {
        return billTime;
    }

    public void setBillTime(String billTime) {
        this.billTime = billTime;
    }
}
