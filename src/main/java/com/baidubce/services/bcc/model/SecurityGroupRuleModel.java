/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bcc.model;

/**
 * The model about the rule of the securitygroup.
 * The tuple of (remark, protocol, direction, portRange, sourceIp|destIp, sourceGroupId|destGroupId) must be unique.
 */
public class SecurityGroupRuleModel {

    /**
     * The remark for the rule
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

    /**
     * The id of the securitygroup for the rule.
     */
    private String securityGroupId;

    /**
     * The id of the specified security group rule
     */
    private String securityGroupRuleId;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public SecurityGroupRuleModel withRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public SecurityGroupRuleModel withDirection(String direction) {
        this.direction = direction;
        return this;
    }

    public String getEthertype() {
        return ethertype;
    }

    public void setEthertype(String ethertype) {
        this.ethertype = ethertype;
    }

    public SecurityGroupRuleModel withEthertype(String ethertype) {
        this.ethertype = ethertype;
        return this;
    }

    public String getPortRange() {
        return portRange;
    }

    public void setPortRange(String portRange) {
        this.portRange = portRange;
    }

    public SecurityGroupRuleModel withPortRange(String portRange) {
        this.portRange = portRange;
        return this;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public SecurityGroupRuleModel withProtocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    public String getSourceGroupId() {
        return sourceGroupId;
    }

    public void setSourceGroupId(String sourceGroupId) {
        this.sourceGroupId = sourceGroupId;
    }

    public SecurityGroupRuleModel withSourceGroupId(String sourceGroupId) {
        this.sourceGroupId = sourceGroupId;
        return this;
    }

    public String getSourceIp() {
        return sourceIp;
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }

    public SecurityGroupRuleModel withSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
        return this;
    }

    public String getDestGroupId() {
        return destGroupId;
    }

    public void setDestGroupId(String destGroupId) {
        this.destGroupId = destGroupId;
    }

    public SecurityGroupRuleModel withDestGroupId(String destGroupId) {
        this.destGroupId = destGroupId;
        return this;
    }

    public String getDestIp() {
        return destIp;
    }

    public void setDestIp(String destIp) {
        this.destIp = destIp;
    }

    public SecurityGroupRuleModel withDestIp(String destIp) {
        this.destIp = destIp;
        return this;
    }

    public String getSecurityGroupId() {
        return securityGroupId;
    }

    public void setSecurityGroupId(String securityGroupId) {
        this.securityGroupId = securityGroupId;
    }

    public String getSecurityGroupRuleId() {
        return this.securityGroupRuleId;
    }

    public void setSecurityGroupRuleId(String securityGroupRuleId) {
        this.securityGroupRuleId = securityGroupRuleId;
    }

    public SecurityGroupRuleModel withSecurityGroupId(String securityGroupId) {
        this.securityGroupId = securityGroupId;
        return this;
    }

    @Override
    public String toString() {
        return "SecurityGroupRuleModel{"
                + "remark='" + remark + '\''
                + ", direction='" + direction + '\''
                + ", ethertype='" + ethertype + '\''
                + ", portRange='" + portRange + '\''
                + ", protocol='" + protocol + '\''
                + ", sourceGroupId='" + sourceGroupId + '\''
                + ", sourceIp='" + sourceIp + '\''
                + ", destGroupId='" + destGroupId + '\''
                + ", destIp='" + destIp + '\''
                + ", securityGroupId='" + securityGroupId + '\''
                + '}';
    }
}
