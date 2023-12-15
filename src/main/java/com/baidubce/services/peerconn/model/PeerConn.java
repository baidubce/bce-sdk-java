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
package com.baidubce.services.peerconn.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The peer connection instance.
 */
@Getter
@Setter
@ToString
public class PeerConn {

    /**
     * The peerConnId of peer conn.
     */
    private String peerConnId;

    /**
     * The role of peer conn.
     */
    private String role;

    /**
     * The status of peer conn.
     */
    private String status;

    /**
     * The bandwidthInMbps of peer conn.
     */
    private int bandwidthInMbps;

    /**
     * The description of peer conn.
     */
    private String description;

    /**
     * The localIfId of peer conn.
     */
    private String localIfId;

    /**
     * The localIfName of peer conn.
     */
    private String localIfName;

    /**
     * The localVpcId of peer conn.
     */
    private String localVpcId;

    /**
     * The localRegion of peer conn.
     */
    private String localRegion;

    /**
     * The peerAccountId of peer conn.
     */
    private String peerAccountId;

    /**
     * The peerVpcId of peer conn.
     */
    private String peerVpcId;

    /**
     * The peerRegion of peer conn.
     */
    private String peerRegion;

    /**
     * The paymentTiming of peer conn.
     */
    private String paymentTiming;

    /**
     * The dnsStatus of peer conn.
     */
    private String dnsStatus;

    /**
     * The createdTime of peer conn.
     */
    private String createdTime;

    /**
     * The expiredTime of peer conn.
     */
    private String expiredTime;
}
