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

import java.util.List;


public class AuthorizeServerEventResp extends BaseResp {

    /**
     * List of planned maintenance event IDs associated with successful authorization
     */
    private List<String> associatedPlannedMaintenanceServerEventIds;

    /**
     * List of unplanned maintenance event IDs associated with successful authorization
     */
    private List<String> associatedUnplannedMaintenanceServerEventIds;

    public void setAssociatedPlannedMaintenanceServerEventIds(List<String> associatedPlannedMaintenanceServerEventIds) {
        this.associatedPlannedMaintenanceServerEventIds = associatedPlannedMaintenanceServerEventIds;
    }

    public List<String> getAssociatedPlannedMaintenanceServerEventIds() {
        return this.associatedPlannedMaintenanceServerEventIds;
    }

    public void setAssociatedUnplannedMaintenanceServerEventIds(List<String> associatedUnplannedMaintenanceServerEventIds) {
        this.associatedUnplannedMaintenanceServerEventIds = associatedUnplannedMaintenanceServerEventIds;
    }

    public List<String> getAssociatedUnplannedMaintenanceServerEventIds() {
        return this.associatedUnplannedMaintenanceServerEventIds;
    }

    @Override
    public String toString() {
        return "AuthorizeServerEventResp{"
                + "associatedPlannedMaintenanceServerEventIds=" + associatedPlannedMaintenanceServerEventIds + "\n"
                + "associatedUnplannedMaintenanceServerEventIds=" + associatedUnplannedMaintenanceServerEventIds + "\n"
                + "}";
    }

}