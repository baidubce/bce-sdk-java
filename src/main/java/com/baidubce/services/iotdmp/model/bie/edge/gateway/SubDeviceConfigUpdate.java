/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
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
package com.baidubce.services.iotdmp.model.bie.edge.gateway;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubDeviceConfigUpdate {
    private IpcServiceConfig ipc;
    private Modbus modbus;
    private Opcua opcua;
    private Opcda opcda;
    private IEC104 iec104;
    private Bacnet bacnet;
    private String custom;


    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class IpcServiceConfig {
        private String name;
        private boolean system;
        private String remoteAddress;
        private boolean agentEnable;
        private String serviceName;
        private String streamAddress;
        private String resultTopic;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Opcda {
        private int interval;
        private String channelId;
        private String group;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Opcua {
        private int interval;
        private int nsOffset;
        private int idOffset;
        private String group;
        private String channelId;

    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Modbus {
        private int slaveId;
        private int interval;
        private String channelId;

    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class IEC104 {
        private String channelId;
        private int doOffset;
        private int diOffset;
        private int piOffset;
        private int aiOffset;
        private int aoOffset;
        private int interval;

    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Bacnet {
        private int interval;
        private int deviceId;
        private String channelId;
        private int addressOffset;

    }
}
