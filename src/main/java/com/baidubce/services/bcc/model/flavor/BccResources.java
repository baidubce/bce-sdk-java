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

import java.util.ArrayList;
import java.util.List;

public class BccResources {

    private List<FlavorGroup> flavorGroups;

    public List<FlavorGroup> getFlavorGroups() {
        return flavorGroups;
    }

    public void setFlavorGroups(List<FlavorGroup> flavorGroups) {
        this.flavorGroups = flavorGroups;
    }

    @Override
    public String toString() {
        return "BccResources{" +
                "flavorGroups=" + flavorGroups +
                '}';
    }

    public static class FlavorGroup {

        private String groupId;
        private List<BccFlavor> flavors = new ArrayList<BccFlavor>();

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public List<BccFlavor> getFlavors() {
            return flavors;
        }

        public void setFlavors(List<BccFlavor> flavors) {
            this.flavors = flavors;
        }

        @Override
        public String toString() {
            return "FlavorGroup{" +
                    "groupId='" + groupId + '\'' +
                    ", flavors=" + flavors +
                    '}';
        }
    }
}
