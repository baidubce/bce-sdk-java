package com.baidubce.services.iothisk.model;

import java.util.List;

/**
 * Represent the response of get batch create status.
 */
public class GetBatchCreateStatusResponse extends IotPkiManageResponse {

    /**
     * Status of the create batch.
     */
    private JobStatus jobStatus;

    /**
     * Show the fail detail when create some client certs failed.
     */
    private String message;

    /**
     * Cert count of the create batch.
     */
    private int certCount;

    /**
     * Handled cert count of the create batch.
     */
    private int finishCount;

    /**
     * Created cert ID list, contains device ID and cert ID.
     */
    private List<DeviceIdAndCertId> certIds ;

    /**
     * Certs zip download url of the create batch.
     */
    private String downloadUrl;

    public boolean isSucceed() {
        if (jobStatus == null) {
            return false;
        }
        return jobStatus.equals(JobStatus.Succeed);
    }

    public boolean isPartialSucceed() {
        if (jobStatus == null) {
            return false;
        }
        return jobStatus.equals(JobStatus.PartialSucceed);
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCertCount() {
        return certCount;
    }

    public void setCertCount(int certCount) {
        this.certCount = certCount;
    }

    public int getFinishCount() {
        return finishCount;
    }

    public void setFinishCount(int finishCount) {
        this.finishCount = finishCount;
    }

    public List<DeviceIdAndCertId> getCertIds() {
        return certIds;
    }

    public void setCertIds(List<DeviceIdAndCertId> certIds) {
        this.certIds = certIds;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public static class DeviceIdAndCertId {

        private String deviceId;

        private String certId;

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getCertId() {
            return certId;
        }

        public void setCertId(String certId) {
            this.certId = certId;
        }
    }

}
