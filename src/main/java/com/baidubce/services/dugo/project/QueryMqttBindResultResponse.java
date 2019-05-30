/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.project;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

public class QueryMqttBindResultResponse extends AbstractBceResponse {
    private List<BindResult> data;

    public List<BindResult> getData() {
        return data;
    }

    public void setData(List<BindResult> data) {
        this.data = data;
    }

    public static class BindResult {
        private String id;
        private String startTime;
        private String endTime;
        private String status;
        private String download;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDownload() {
            return download;
        }

        public void setDownload(String download) {
            this.download = download;
        }
    }
}
