package com.baidubce.services.bcc.model.userop;

public class IssueDiskInfoResponse {
    /**
     * Faulty Disk SN
     */
    private String issueDiskSn;

    public String getIssueDiskSn() {
        return issueDiskSn;
    }

    public void setIssueDiskSn(String issueDiskSn) {
        this.issueDiskSn = issueDiskSn;
    }

    @Override
    public String toString() {
        return "IssueDiskInfoResponse{" +
                "issueDiskSn='" + issueDiskSn + '\'' +
                '}';
    }
}
