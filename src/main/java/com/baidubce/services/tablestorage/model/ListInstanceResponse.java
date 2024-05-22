/*
 * Copyright (c) 2024 Baidu.com, Inc. All Rights Reserved
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

package com.baidubce.services.tablestorage.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent the output of a ListInstance operation.
 */
public class ListInstanceResponse extends AbstractTableStorageResponse {

    private List<InstanceInfo> instances = new ArrayList<InstanceInfo>();

    /**
     * Get list of instance info objects of this list instance response
     *
     * @return The list of instance info objects of this list instance response
     */
    public List<InstanceInfo> getInstances() {
        return instances;
    }

    /**
     * Set the instance info objects to this list instance request.
     *
     * @param instances The list of instance info objects
     */
    public void setInstances(List<InstanceInfo> instances) {
        this.instances = instances;
    }

    /**
     * Convert this object to string
     *
     * @return The string represent of this object
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("ListInstanceResponse [\n");
        for (InstanceInfo info : instances) {
            builder.append(info).append(",\n");
        }
        builder.append("]");
        return builder.toString();
    }
}

/* vim: set expandtab ts=4 sw=4 sts=4 tw=100: */
