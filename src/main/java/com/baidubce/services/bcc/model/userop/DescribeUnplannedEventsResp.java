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


public class DescribeUnplannedEventsResp  extends ListResponse {
    /**
     * The unique BCE identifier for the service request the caller made. The BCE request ID can uniquely identify
     * the BCE request, and is used for reporting an error to BCE support team.
     */
    private String requestId;

    /**
     * List of unplanned maintenance events.
     */
    private List<UnplannedEventResponse> unplannedMaintenanceEvents;

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public void setUnplannedMaintenanceEvents(List<UnplannedEventResponse> unplannedMaintenanceEvents) {
        this.unplannedMaintenanceEvents = unplannedMaintenanceEvents;
    }

    public List<UnplannedEventResponse> getUnplannedMaintenanceEvents() {
        return this.unplannedMaintenanceEvents;
    }

    @Override
    public String toString() {
        return "DescribeUnplannedEventsV3Resp{"
                + "requestId=" + requestId + "\n"
                + "unplannedMaintenanceEvents=" + unplannedMaintenanceEvents + "\n"
                + "}";
    }

}