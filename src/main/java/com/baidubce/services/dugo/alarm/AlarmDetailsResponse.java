/*
 * Copyright 2019 Baidu, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.dugo.alarm;

import java.util.List;
import com.baidubce.model.AbstractBceResponse;

/**
 * Response of querying alarm details
 */
public class AlarmDetailsResponse extends AbstractBceResponse {
    private String alarmId;
    private String name;
    private String des;
    private List<String> batchIds;
    private List<String> vehicleIds;
    private String status;
    private AlarmRule alarmRule;
    private AlarmPolicy alarmPolicy;

    public String getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(String alarmId) {
        this.alarmId = alarmId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public List<String> getBatchIds() {
        return batchIds;
    }

    public void setBatchIds(List<String> batchIds) {
        this.batchIds = batchIds;
    }

    public List<String> getVehicleIds() {
        return vehicleIds;
    }

    public void setVehicleIds(List<String> vehicleIds) {
        this.vehicleIds = vehicleIds;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AlarmRule getAlarmRule() {
        return alarmRule;
    }

    public void setAlarmRule(AlarmRule alarmRule) {
        this.alarmRule = alarmRule;
    }

    public AlarmPolicy getAlarmPolicy() {
        return alarmPolicy;
    }

    public void setAlarmPolicy(AlarmPolicy alarmPolicy) {
        this.alarmPolicy = alarmPolicy;
    }

    public static class AlarmRule {
        private String alarmField;
        private String rule;
        private Double alarmFieldValue;

        public AlarmRule() {
        }

        public AlarmRule(String alarmField, String rule, Double alarmFieldValue) {
            this.alarmField = alarmField;
            this.rule = rule;
            this.alarmFieldValue = alarmFieldValue;
        }

        public String getRule() {
            return rule;
        }

        public void setRule(String rule) {
            this.rule = rule;
        }

        public String getAlarmField() {
            return alarmField;
        }

        public void setAlarmField(String alarmField) {
            this.alarmField = alarmField;
        }

        public Double getAlarmFieldValue() {
            return alarmFieldValue;
        }

        public void setAlarmFieldValue(Double alarmFieldValue) {
            this.alarmFieldValue = alarmFieldValue;
        }
    }

    public static class AlarmPolicy {
        private List<SinkType> sinkTypes;
        private String alarmType;
        private AlarmTypeInfo alarmTypeInfo;

        public AlarmPolicy() {
        }

        public AlarmPolicy(List<SinkType> sinkTypes, String alarmType, AlarmTypeInfo alarmTypeInfo) {
            this.sinkTypes = sinkTypes;
            this.alarmType = alarmType;
            this.alarmTypeInfo = alarmTypeInfo;
        }

        public List<SinkType> getSinkTypes() {
            return sinkTypes;
        }

        public void setSinkTypes(List<SinkType> sinkTypes) {
            this.sinkTypes = sinkTypes;
        }

        public String getAlarmType() {
            return alarmType;
        }

        public void setAlarmType(String alarmType) {
            this.alarmType = alarmType;
        }

        public AlarmTypeInfo getAlarmTypeInfo() {
            return alarmTypeInfo;
        }

        public void setAlarmTypeInfo(AlarmTypeInfo alarmTypeInfo) {
            this.alarmTypeInfo = alarmTypeInfo;
        }
    }

    public static class SinkType {
        private String sinkType;
        private String sinkAddress;

        public SinkType() {
        }

        public SinkType(String sinkType, String sinkAddress) {
            this.sinkType = sinkType;
            this.sinkAddress = sinkAddress;
        }

        public String getSinkType() {
            return sinkType;
        }

        public void setSinkType(String sinkType) {
            this.sinkType = sinkType;
        }

        public String getSinkAddress() {
            return sinkAddress;
        }

        public void setSinkAddress(String sinkAddress) {
            this.sinkAddress = sinkAddress;
        }
    }

    public static class AlarmTypeInfo {

        private Integer frequency;
        private Double window;
        private String unit;

        public Integer getFrequency() {
            return frequency;
        }

        public void setFrequency(Integer frequency) {
            this.frequency = frequency;
        }

        public Double getWindow() {
            return window;
        }

        public void setWindow(Double window) {
            this.window = window;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public enum UnitType {
            SECOND("SECOND");

            String type;

            UnitType(String type) {
                this.type = type;
            }

            public String getType() {
                return this.type;
            }
        }
    }
}
