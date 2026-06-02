/*
 * Copyright 2025 Baidu, Inc. All Rights Reserved
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
package com.baidubce.services.bcc.model.userop;

import com.baidubce.model.ListResponse;

import java.util.List;

public class DescribePlannedEventsResp extends ListResponse {
    /**
     * requestId
     */
    private String requestId;

    /**
     * planned List
     */
    private List<PlannedEventResponse> plannedMaintenanceEvents;

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public void setPlannedMaintenanceEvents(List<PlannedEventResponse> plannedMaintenanceEvents) {
        this.plannedMaintenanceEvents = plannedMaintenanceEvents;
    }

    public List<PlannedEventResponse> getPlannedMaintenanceEvents() {
        return this.plannedMaintenanceEvents;
    }

    @Override
    public String toString() {
        return "DescribePlannedEventsV3Resp{"
                + "requestId=" + requestId + "\n"
                + "plannedMaintenanceEvents=" + plannedMaintenanceEvents + "\n"
                + "}";
    }

}