/*
 * Copyright 2017 Baidu, Inc. All Rights Reserved.
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
package com.baidubce.services.iotdm.model.v3.device;

import com.baidubce.services.iotdm.model.v3.schema.SchemaProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent the response of getting a device view.
 */
public class DeviceViewResponse extends DeviceBasicInfoResponse {

    private int profileVersion;

    private List<DeviceViewAttribute> properties = new ArrayList<DeviceViewAttribute>();

    public int getProfileVersion() {
        return profileVersion;
    }

    public void setProfileVersion(int profileVersion) {
        this.profileVersion = profileVersion;
    }

    public List<DeviceViewAttribute> getProperties() {
        return properties;
    }

    public void setProperties(List<DeviceViewAttribute> properties) {
        this.properties = properties;
    }

    public static class DeviceViewAttribute {

        private String attributeName;

        private String showName;

        private String unit;

        private Long reportedTime;

        private Long desiredTime;

        private String reportedValue;

        private String defaultValue;

        private String desiredValue;

        private SchemaProperty.PropertyType type;

        public String getAttributeName() {
            return attributeName;
        }

        public void setAttributeName(String attributeName) {
            this.attributeName = attributeName;
        }

        public String getShowName() {
            return showName;
        }

        public void setShowName(String showName) {
            this.showName = showName;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public Long getReportedTime() {
            return reportedTime;
        }

        public void setReportedTime(Long reportedTime) {
            this.reportedTime = reportedTime;
        }

        public Long getDesiredTime() {
            return desiredTime;
        }

        public void setDesiredTime(Long desiredTime) {
            this.desiredTime = desiredTime;
        }

        public String getReportedValue() {
            return reportedValue;
        }

        public void setReportedValue(String reportedValue) {
            this.reportedValue = reportedValue;
        }

        public String getDefaultValue() {
            return defaultValue;
        }

        public void setDefaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
        }

        public String getDesiredValue() {
            return desiredValue;
        }

        public void setDesiredValue(String desiredValue) {
            this.desiredValue = desiredValue;
        }

        public SchemaProperty.PropertyType getType() {
            return type;
        }

        public void setType(SchemaProperty.PropertyType type) {
            this.type = type;
        }

    }

}
