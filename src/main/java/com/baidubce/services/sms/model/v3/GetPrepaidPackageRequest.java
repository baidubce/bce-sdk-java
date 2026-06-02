package com.baidubce.services.sms.model.v3;

import com.baidubce.services.sms.model.SmsRequest;

public class GetPrepaidPackageRequest extends SmsRequest {
    /**
     * The userId indicates the user ID of the prepaid package, required.
     */
    private String userId;

    /**
     * The countryType indicates the countries or regions in which the prepaid package can be used.<br/>
     * The value of countryType could be "domestic", "international", "global".<br/>
     * "domestic" means the prepaid package used in Mainland China.<br/>
     * "international" means the prepaid package used outside Mainland China.<br/>
     * "global" means the prepaid package used in all countries.
     */
    private String countryType;

    /**
     * The Status of prepaid package, optional.<br/>
     * The value of packageStatus could be "RUNNING", "EXPIRED", "USED_UP", "DESTROYED".<br/>
     * "RUNNING" means the prepaid package is running.<br/>
     * "EXPIRED" means the prepaid package is expired.<br/>
     * "USED_UP" means the prepaid package is used up.<br/>
     * "DESTROYED" means the prepaid package is destroyed.
     */
    private String packageStatus;

    /**
     * pageNo of prepaid package, optional.
     */
    private String pageNo;

    /**
     * pageSize of prepaid package, optional.
     */
    private String pageSize;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCountryType() {
        return countryType;
    }

    public void setCountryType(String countryType) {
        this.countryType = countryType;
    }

    public String getPackageStatus() {
        return packageStatus;
    }

    public void setPackageStatus(String packageStatus) {
        this.packageStatus = packageStatus;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
