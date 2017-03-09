package com.baidubce.services.tsdb.model;

import java.util.Date;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.tsdb.TsdbConstants;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Represent the response for getting database.
 */
public class GetDatabaseResponse extends AbstractBceResponse {

    private String databaseId;
    private String databaseName;
    private String description;
    private String endpoint;
    private Quota quota;
    private String status;
    private Boolean autoExport;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = TsdbConstants.DATETIME_FORMAT, timezone = "UTC")
    private Date createTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = TsdbConstants.DATETIME_FORMAT, timezone = "UTC")
    private Date expiredTime;

    public String getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(String databaseId) {
        this.databaseId = databaseId;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public Quota getQuota() {
        return quota;
    }

    public void setQuota(Quota quota) {
        this.quota = quota;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getAutoExport() {
        return autoExport;
    }

    public void setAutoExport(Boolean autoExport) {
        this.autoExport = autoExport;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GetDatabaseResponse)) {
            return false;
        }

        GetDatabaseResponse that = (GetDatabaseResponse) o;

        if (databaseId != null ? !databaseId.equals(that.databaseId) : that.databaseId != null) {
            return false;
        }
        if (databaseName != null ? !databaseName.equals(that.databaseName) : that.databaseName != null) {
            return false;
        }
        if (description != null ? !description.equals(that.description) : that.description != null) {
            return false;
        }
        if (endpoint != null ? !endpoint.equals(that.endpoint) : that.endpoint != null) {
            return false;
        }
        if (quota != null ? !quota.equals(that.quota) : that.quota != null) {
            return false;
        }
        if (status != null ? !status.equals(that.status) : that.status != null) {
            return false;
        }
        if (autoExport != null ? !autoExport.equals(that.autoExport) : that.autoExport != null) {
            return false;
        }
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) {
            return false;
        }
        return expiredTime != null ? expiredTime.equals(that.expiredTime) : that.expiredTime == null;
    }
}
