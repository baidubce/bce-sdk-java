/*
 * Copyright 2020 Baidu, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.as.model.asgroup;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * The response of increasing the asGroup.
 */
public class IncreaseAsGroupResponse extends AbstractBceResponse {

    /**
     * Auto scaling group id.
     */
    private String groupId;

    /**
     * List of newly added instance IDs in the auto scaling group.
     */
    private List<String> instanceId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<String> getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(List<String> instanceId) {
        this.instanceId = instanceId;
    }
}
