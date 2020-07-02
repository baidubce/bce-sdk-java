/*
 * Copyright 2019 Baidu, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.lss.model;

import com.baidubce.model.AbstractBceResponse;

/**
 * clip recording file response
 *
 * @Author: yangwenyue
 * @Date: Created in 2019-08-28 15:47
 */
public class RecordingClipResponse extends AbstractBceResponse {

    private String fileUrl;

    private String jobId;

    private String clipId;

    private String message;

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getClipId() {
        return clipId;
    }

    public void setClipId(String clipId) {
        this.clipId = clipId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "LssRecordingClipResponse{" +
                "fileUrl='" + fileUrl + '\'' +
                ", jobId='" + jobId + '\'' +
                ", clipId='" + clipId + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}