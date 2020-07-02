/*
 * Copyright (c) 2020 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bcc.model.flavor;

import java.util.List;

/**
 * The information of the zone resource.
 */
public class ZoneResource {
    private String zoneName;

    private List<BccBidResources> bccResources;

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public List<BccBidResources> getBccResources() {
        return bccResources;
    }

    public void setBccResources(List<BccBidResources> bccResources) {
        this.bccResources = bccResources;
    }

    @Override
    public String toString() {
        return "ZoneResource{" +
                "zoneName='" + zoneName + "\'" +
                ", bccResources=" + bccResources +
                '}';
    }
}
