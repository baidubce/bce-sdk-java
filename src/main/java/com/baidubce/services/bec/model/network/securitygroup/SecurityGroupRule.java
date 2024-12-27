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
package com.baidubce.services.bec.model.network.securitygroup;

import lombok.Data;

/**
 * @Author zhangyongchao01
 * @Since 2024-11-15 11:46
 * @Version v1.0
 * <p>
 * The security group rule.
 */
@Data
public class SecurityGroupRule {

    /**
     * The ether type, the value must be IPv4/IPv6.
     */
    private String etherType;

    /**
     * The restrictive direction, the value must be ingress/egress.
     */
    private String direction;

    /**
     * Protocol, the value must be all|tcp|udp|icmp.
     */
    private String protocol;

    /**
     * Port range, default 1-65535.
     */
    private String portRange;

    /**
     * Source ip address.
     * Only used in the scene direction is ingress.
     */
    private String sourceIp;

    /**
     * Destination ip address.
     * Only used in the scene direction is egress.
     */
    private String destIp;

    /**
     * The remark.
     */
    private String remark;
}