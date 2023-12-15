package com.baidubce.services.et.model;

import com.baidubce.model.AbstractBceResponse;

import lombok.ToString;

/**
 * The model for describing ET.
 */
@ToString
public class Et extends AbstractBceResponse {
    /**
     * ET(ID)
     */
    private String id;

    /**
     * Name
     */
    private String name;

    /**
     * Description
     */
    private String description;

    /**
     * Express status. Its value range is ack-wait/accept/reject/building/pay-wait/established/stopped/deleted, which
     * respectively correspond to Applying/Application accepted/Application rejected/Constructing/Unpaid (the unpaid
     * port duration fee for the completed construction)/Available/Expired/Deleting after the expiry
     */
    private String status;

    /**
     * Expiration time
     */
    private String expireTime;

    /**
     * Operator. Its value range is ISP_CMCC/ISP_CUCC/ISP_CTC/ISP_CHH/ISP_OTHER, which respectively correspond to
     * China Mobile/China Unicom/China Telecom/Hosted Express/Others in China
     */
    private String isp;

    /**
     * The interface specification, of which the value range is 1G/10G/100G
     */
    private String intfType;

    /**
     * Access Type
     */
    private String apType;

    /**
     * Access Point
     */
    private String apAddr;

    /**
     * Peer address
     */
    private String userIdc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getIntfType() {
        return intfType;
    }

    public void setIntfType(String intfType) {
        this.intfType = intfType;
    }

    public String getApType() {
        return apType;
    }

    public void setApType(String apType) {
        this.apType = apType;
    }

    public String getApAddr() {
        return apAddr;
    }

    public void setApAddr(String apAddr) {
        this.apAddr = apAddr;
    }

    public String getUserIdc() {
        return userIdc;
    }

    public void setUserIdc(String userIdc) {
        this.userIdc = userIdc;
    }
}
