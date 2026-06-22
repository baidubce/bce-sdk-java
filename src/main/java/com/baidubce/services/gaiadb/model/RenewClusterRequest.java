package com.baidubce.services.gaiadb.model;

public class RenewClusterRequest extends GenericGaiadbRequest {
    private String instanceId;
    private Integer duration;
    private String productType;
    private Boolean isDirectPay;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Boolean getIsDirectPay() {
        return isDirectPay;
    }

    public void setIsDirectPay(Boolean directPay) {
        isDirectPay = directPay;
    }
}
