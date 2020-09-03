/*
 * Copyright 2019-2020 Baidu, Inc.
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
package com.baidubce.services.bvw.model.videoedit;

public class PollingDataModel {
    /**
     * the video edit id
     */
    private long editId;
    /**
     * the video edit status: SUCCESS FAILED READY
     */
    private String editStatus;
    /**
     * the bos bucket
     */
    private String bucket;
    /**
     * the bos key
     */
    private String key;
    /**
     * the edit task createTime
     */
    private String createTime;
    /**
     * the edit task UpTime
     */
    private String updateTime;

    public PollingDataModel() {
    }

    public PollingDataModel(long editId, String editStatus, String bucket, String key,
                            String createTime, String updateTime) {
        this.editId = editId;
        this.editStatus = editStatus;
        this.bucket = bucket;
        this.key = key;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public long getEditId() {
        return editId;
    }

    public void setEditId(long editId) {
        this.editId = editId;
    }

    public String getEditStatus() {
        return editStatus;
    }

    public void setEditStatus(String editStatus) {
        this.editStatus = editStatus;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

}
