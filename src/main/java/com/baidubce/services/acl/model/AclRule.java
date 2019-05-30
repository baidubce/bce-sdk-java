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
package com.baidubce.services.acl.model;

/**
 * aclRule detail info model
 */
public class AclRule {

    /**
     * the id of the aclRule
     */
    private String id;

    /**
     * the subnet id of the aclRule
     */
    private String subnetId;

    /**
     * the name of the aclRule
     */
    private String name;

    /**
     * the option param to describe the aclRule
     */
    private String description;

    /**
     * the protocol of the aclRule<br>
     * contains "all", "tcp", "udp", "icmp"
     */
    private String protocol;

    /**
     * the source ip address of the aclRule<br>
     * can be a specific ip or value "all"
     */
    private String sourceIpAddress;

    /**
     * the destination ip address of the aclRule<br>
     * can be a specific ip or value "all"
     */
    private String destinationIpAddress;

    /**
     * the source port of the aclRule
     */
    private String sourcePort;

    /**
     * the destination port of the aclRule
     */
    private String destinationPort;

    /**
     * the position of the aclRule<br>
     * the value can only be one of 1-5000<br>
     * aclRule's priority is higher with the smaller position value
     */
    private Integer position;

    /**
     * the direction of the aclRule<br>
     * value contains "ingress" and "egress"
     */
    private String direction;

    /**
     * the strategy of the aclRule<br>
     * value contains "allow" and "deny"
     */
    private String action;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubnetId() {
        return subnetId;
    }

    public void setSubnetId(String subnetId) {
        this.subnetId = subnetId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getSourceIpAddress() {
        return sourceIpAddress;
    }

    public void setSourceIpAddress(String sourceIpAddress) {
        this.sourceIpAddress = sourceIpAddress;
    }

    public String getDestinationIpAddress() {
        return destinationIpAddress;
    }

    public void setDestinationIpAddress(String destinationIpAddress) {
        this.destinationIpAddress = destinationIpAddress;
    }

    public String getSourcePort() {
        return sourcePort;
    }

    public void setSourcePort(String sourcePort) {
        this.sourcePort = sourcePort;
    }

    public String getDestinationPort() {
        return destinationPort;
    }

    public void setDestinationPort(String destinationPort) {
        this.destinationPort = destinationPort;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
