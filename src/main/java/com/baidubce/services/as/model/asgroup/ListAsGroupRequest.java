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

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.ListRequest;

/**
 * The request for getting the asGroup list.
 */
public class ListAsGroupRequest extends ListRequest {

    /**
     * The name of auto scaling group.
     */
    private String groupName;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "ListAsGroupRequest{" +
                "marker='" + this.getMarker() + '\'' +
                ", maxKeys=" + this.getMaxKeys() +
                ", groupName='" + groupName + '\'' +
                '}';
    }

    @Override
    public ListAsGroupRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
