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
 * The response for blb's enterpriseSecurityGroup.
 */
@Getter
@Setter
@ToString
public class BlbEsgResponse extends AbstractBceResponse {

    /**
     * the short id of the enterpriseSecurityGroup.
     */
    private String enterpriseSecurityGroupId;
    /**
     * the name of enterpriseSecurityGroup.
     */
    private String enterpriseSecurityGroupName;
    /**
     * the description of enterpriseSecurityGroup.
     */
    private String enterpriseSecurityGroupDesc;
    /**
     * the enterpriseSecurityGroupRules of blb esg.
     */
    private List<BlbEsgRule> enterpriseSecurityGroupRules;

    @Getter
    @Setter
    @ToString
    public static class BlbEsgRule {

        /**
         * The esg rule remark.
         */
        private String remark;

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
         * The source ip range with CIDR formats. The default value 0.0.0.0/0 (allow all ip address),
         * other supported formats such as 10.159.6.18/12 or 10.159.6.186. Only supports IPV4.
         * Only works for  direction = "ingress"
         */
        private String sourceIp;

        /**
         * The destination ip range with CIDR formats. The default value 0.0.0.0/0 (allow all ip address),
         * other supported formats such as 10.159.6.18/12 or 10.159.6.186. Only supports IPV4.
         * Only works for  direction = "egress"
         */
        private String destIp;

        /**
         * Allow or deny strategy, Available value are allow,deny
         */
        private String action;

        /**
         * Priority of enterprise security group,the smaller value is,the higher priority is
         * if allow strategy and deny strategy have the same priority,deny strategy takes precedence
         * Available range is rang [1, 1000]
         */
        private Integer priority;

        /**
         * The id of enterprise security group rule.
         */
        private String enterpriseSecurityGroupRuleId;
    }
}
