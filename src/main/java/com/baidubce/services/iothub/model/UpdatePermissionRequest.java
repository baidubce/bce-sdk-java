/*
 * Copyright 2016 Baidu, Inc.
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
package com.baidubce.services.iothub.model;

import java.util.List;

/**
 * Represent the request to update the permission.
 */
public class UpdatePermissionRequest extends BaseRequest {

    private String permissionUuid;

    private List<Operation> operations;

    private String topic;

    public  UpdatePermissionRequest withPermissionUuid(String permissionUuid) {
        this.permissionUuid = permissionUuid;
        return this;
    }

    public  UpdatePermissionRequest withOperations(List<Operation> operations) {
        this.operations = operations;
        return this;
    }

    public  UpdatePermissionRequest withTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public UpdatePermissionRequest withEndpointName(String endpointName) {
        this.setEndpointName(endpointName);
        return this;
    }

    public String getPermissionUuid() {
        return permissionUuid;
    }

    public void setPermissionUuid(String permissionUuid) {
        this.permissionUuid = permissionUuid;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
