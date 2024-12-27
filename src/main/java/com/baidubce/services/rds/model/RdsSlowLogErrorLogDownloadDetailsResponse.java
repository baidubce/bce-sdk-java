package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;
import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsSlowLogErrorLogDownloadDetailsResponse extends AbstractBceResponse {
    private String url;
    private String downloadExpires;
    private String dataBackupType;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDownloadExpires() {
        return downloadExpires;
    }

    public void setDownloadExpires(String downloadExpires) {
        this.downloadExpires = downloadExpires;
    }

    public String getDataBackupType() {
        return dataBackupType;
    }

    public void setDataBackupType(String dataBackupType) {
        this.dataBackupType = dataBackupType;
    }
}
