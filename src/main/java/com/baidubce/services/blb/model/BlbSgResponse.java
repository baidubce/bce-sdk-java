/*
 * Copyright (c) 2019 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.blb.model;

import com.baidubce.model.AbstractBceResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * The response for blb's securityGroup.
 */
@Getter
@Setter
@ToString
public class BlbSgResponse extends AbstractBceResponse {

    /**
     * the short id of the securityGroup.
     */
    private String securityGroupId;
    /**
     * the name of securityGroup.
     */
    private String securityGroupName;
    /**
     * the description of securityGroup.
     */
    private String securityGroupDesc;
    /**
     * the name of blb's vpc.
     */
    private String vpcName;
    /**
     * the securityGroupRules of blb sg.
     */
    private List<BlbSgRule> securityGroupRules;

    @Getter
    @Setter
    @ToString
    public static class BlbSgRule {
        /**
         * The sg rule id.
         */
        private String securityGroupRuleId;

        /**
         * The parameter to define the rule direction,available value are "ingress/egress"
         */
        private String direction;

        /**
         * The ethernet protocol
         */
        private String ethertype;

        /**
         * The port range to specify the port which the rule will work on.
         * Available range is rang [0, 65535],"" for all port.
         */
        private String portRange;

        /**
         * The parameter specify which protocol will the rule work on, the fault value is "" for all protocol.
         * Available protocol are tcp,udp and icmp.
         */
        private String protocol = "";

        /**
         * The id of source securitygroup.
         * Only works for  direction = "ingress"
         */
        private String sourceGroupId;

        /**
         * The source ip range with CIDR formats. The default value 0.0.0.0/0 (allow all ip address),
         * other supported formats such as 10.159.6.18/12 or 10.159.6.186. Only supports IPV4.
         * Only works for  direction = "ingress"
         */
        private String sourceIp;

        /**
         * The id of destination securitygroup.
         * Only works for  direction = "egress"
         */
        private String destGroupId;

        /**
         * The destination ip range with CIDR formats. The default value 0.0.0.0/0 (allow all ip address),
         * other supported formats such as 10.159.6.18/12 or 10.159.6.186. Only supports IPV4.
         * Only works for  direction = "egress"
         */
        private String destIp;
    }
}
