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

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetEdgeGatewayNodeDetailResponse extends AbstractBceResponse {
    private String namespace;
    private String name;
    private String version;
    private String createTime;
    private String accelerator;
    private Map<String, String> labels;
    private Map<String, String> annotations;
    private String appMode;
    private List<String> sysApps;
    private String description;
    private boolean cluster;
    private int ready;
    private String mode;
    private String nodeMode;
    private String link;
    private String coreId;
    private Desire desire;
    private Report report;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Desire {
        private List<App> apps;
        private List<Device> devices;
        private List<App> sysapps;

    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Report {
        private String time;
        private List<App> apps;
        private List<App> sysapps;
        private Core core;
        private List<AppStat> appstats;
        private List<AppStat> sysappstats;
        private Map<String, NodeInfo> node;
        private Map<String, NodeStats> nodestats;
        private Map<String, Integer> nodeinsnum;
        private String modeinfo;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class AppStat {
            private String name;
            private String version;
            private String deployType;
            private String status;
            private Map<String, Instance> instances;

            @Data
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Instance {
                private String name;
                private String appName;
                private Usage usage;
                private String status;
                private String ip;
                private String nodeName;
                private String createTime;
                private List<Container> containers;

                @Data
                @JsonIgnoreProperties(ignoreUnknown = true)
                public static class Usage {
                    private String cpu;
                    private String memory;
                }

                @Data
                @JsonIgnoreProperties(ignoreUnknown = true)
                public static class Container {
                    private String name;
                    private Usage usage;
                    private String state;
                }
            }
        }

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class NodeInfo {
            private String hostname;
            private String address;
            private String arch;
            private String kernelVer;
            private String os;
            private String containerRuntime;
            private String machineID;
            private String bootID;
            private String systemUUID;
            private String osImage;
            private String role;
            private Map<String, String> labels;
            private String clientIP;

            // Getters and setters
        }

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class NodeStats {
            private boolean ready;
            private Usage usage;
            private Capacity capacity;
            private Percent percent;
            private NetIO netio;
            private Extension extension;

            @Data
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Usage {
                private String cpu;
                private String disk;
                private String memory;
            }

            @Data
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Capacity {
                private String cpu;
                private String disk;
                private String memory;
            }

            @Data
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Percent {
                private String cpu;
                private String disk;
                private String memory;
            }

            @Data
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class NetIO {
                private String netBytesRecv;
                private String netBytesSent;
                private String netPacketsRecv;
                private String netPacketsSent;
            }

            @Data
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Extension {
                private double diskPercent;
                private long diskTotal;
                private long diskUsed;
                private long netBytesRecv;
                private long netBytesSent;
                private long netPacketsRecv;
                private long netPacketsSent;
            }
        }
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class App {
        private String name;
        private String version;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Device {
        private String name;
        private String version;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SysApp {
        private String name;
        private String version;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Core {
        private String goVersion;
        private String binVersion;
        private String gitRevision;
    }
}
