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

import com.baidubce.services.iotdmp.model.bie.protocol.BiePortProtocol;
import com.baidubce.services.iotdmp.model.bie.protocol.BiePortServiceType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppInfoService {
    private String name;
    private String image;
    private List<Port> ports;
    private List<Env> env;
    private List<VolumeMounts> volumeMounts;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Port {
        private Integer containerPort;
        private BiePortServiceType serviceType;
        private Integer hostPort;
        private Integer nodePort;
        private BiePortProtocol protocol;

        public Port update(Port other) {
            this.hostPort = other.getHostPort();
            this.nodePort = other.getNodePort();
            this.containerPort = other.getContainerPort();
            return this;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Env {
        private String name;
        private String value;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class VolumeMounts {
        private String name;
        private String mountPath;
        private String subPath;
        private boolean readOnly = true;
    }
}
