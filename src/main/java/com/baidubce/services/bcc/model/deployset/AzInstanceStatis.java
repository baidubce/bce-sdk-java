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
package com.baidubce.services.bcc.model.deployset;

/**
 * The response for getting the deploy set list.
 */
public class AzInstanceStatis {

    private int instanceCount;

    private int instanceTotal;

    private String zoneName;

    public int getInstanceCount() {
        return instanceCount;
    }

    public void setInstanceCount(int instanceCount) {
        this.instanceCount = instanceCount;
    }

    public int getInstanceTotal() {
        return instanceTotal;
    }

    public void setInstanceTotal(int instanceTotal) {
        this.instanceTotal = instanceTotal;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    @Override
    public String toString() {
        return "AzInstanceStatis{" +
                "instanceCount=" + instanceCount +
                ", instanceTotal=" + instanceTotal +
                ", zoneName='" + zoneName + '\'' +
                '}';
    }
}
