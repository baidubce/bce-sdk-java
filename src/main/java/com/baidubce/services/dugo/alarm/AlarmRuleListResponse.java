/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.alarm;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * Query alarm list response
 */
public class AlarmRuleListResponse extends AbstractBceResponse {
    private Meta meta;
    private List<AlarmDigest> data;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<AlarmDigest> getData() {
        return data;
    }

    public void setData(List<AlarmDigest> data) {
        this.data = data;
    }

    public static class Meta {
        private long total;

        public long getTotal() {
            return total;
        }

        public void setTotal(long total) {
            this.total = total;
        }
    }

    public static class AlarmDigest {
        private String alarmId;
        private String name;
        private String des;
        private String status;

        public String getAlarmId() {
            return alarmId;
        }

        public void setAlarmId(String alarmId) {
            this.alarmId = alarmId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
