package com.baidubce.services.rds.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Date;

public class RdsConfigItemModifyHistory implements Comparable<RdsConfigItemModifyHistory> {
    private String name;
    private String beforeValue;
    private String afterValue;
    private String status;
    private Date updateTime;

    @Override
    public int compareTo(RdsConfigItemModifyHistory o) {
        if (o.updateTime == null && this.updateTime == null) {
            return 0;
        }
        if (o.updateTime == null) {
            return -1; // this排在 o 前面（o 视为最旧）
        }
        if (this.updateTime == null) {
            return 1; // o 排在 this 前面（this 视为最旧）
        }
        return o.updateTime.compareTo(this.updateTime);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RdsConfigItemModifyHistory{");
        sb.append("name='").append(name).append('\'');
        sb.append(", beforeValue='").append(beforeValue).append('\'');
        sb.append(", afterValue='").append(afterValue).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", updateTime=").append(updateTime);
        sb.append('}');
        return sb.toString();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeforeValue() {
        return beforeValue;
    }

    public void setBeforeValue(String beforeValue) {
        this.beforeValue = beforeValue;
    }

    public String getAfterValue() {
        return afterValue;
    }

    public void setAfterValue(String afterValue) {
        this.afterValue = afterValue;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    public Date getUpdateTime() {
        return updateTime;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public static class ConfigItemModifyHistoryList extends ArrayList<RdsConfigItemModifyHistory> {
    }
}
