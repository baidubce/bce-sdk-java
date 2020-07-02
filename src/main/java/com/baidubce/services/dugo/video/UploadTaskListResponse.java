/*
 * Copyright 2019 Baidu, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.dugo.video;

import java.util.Date;
import java.util.List;

import com.baidubce.model.AbstractBceResponse;

/**
 * upload task list response
 *
 * @Author: shihuike
 * @Date: Created in 2019-08-01 17:14
 */
public class UploadTaskListResponse extends AbstractBceResponse {
    private Meta meta;
    private List<UploadTaskData> data;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<UploadTaskData> getData() {
        return data;
    }

    public void setData(List<UploadTaskData> data) {
        this.data = data;
    }

    public static class UploadTaskData {
        private String vehicleId;
        private String vehicleName;
        private String groupName;
        private String status;
        private String taskUuid;
        private Date createTime;

        public String getVehicleId() {
            return vehicleId;
        }

        public void setVehicleId(String vehicleId) {
            this.vehicleId = vehicleId;
        }

        public String getVehicleName() { return vehicleName; }

        public void setVehicleName(String vehicleName) {
            this.vehicleName = vehicleName;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTaskUuid() {
            return taskUuid;
        }

        public void setTaskUuid(String taskUuid) {
            this.taskUuid = taskUuid;
        }

        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }
    }
}
