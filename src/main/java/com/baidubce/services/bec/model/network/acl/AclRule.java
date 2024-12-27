/*
 * Copyright (c) 2024 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bec.model.network.acl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Author zhangyongchao01
 * @Since 2024-11-12 19:25
 * @Version v1.0
 * The acl rule.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AclRule {

    /**
     * The subnet id.
     */
    private String subnetId;

    /**
     * The description.
     */
    private String description;

    /**
     * Protocol, the value must be all|tcp|udp|icmp.
     */
    private String protocol;

    /**
     * Source ip address.
     * ACL egress source ip address must be in subnet.
     * Can take the value "all", represent all ip.
     */
    private String sourceIpAddress;

    /**
     * Destination ip address.
     * ACL ingress destination ip address must be in subnet.
     * Can take the value "all", represent all ip.
     */
    private String destinationIpAddress;

    /**
     * Source port, the value range is 0-65535.
     */
    private String sourcePort;

    /**
     * Destination port, the value range is 0-65535.
     */
    private String destinationPort;

    /**
     * The priority, the value range is 1-32768.
     * The smaller the value, the higer the priority.
     */
    private Integer position;

    /**
     * The restrictive direction, the value must be ingress/egress.
     */
    private String direction;

    /**
     * The ether type, the value must be IPv4/IPv6.
     */
    private String etherType;

    /**
     * The action policy, the value must be "allow/deny".
     */
    private String action;

    /**
     * Whether is default.
     */
    @JsonProperty("isDefault")
    private Boolean isDefault;

    /**
     * Whether is valid.
     */
    private Boolean isValid;
}