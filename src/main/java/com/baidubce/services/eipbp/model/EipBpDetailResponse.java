/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.eipbp.model;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bcc.model.TagModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * The response for get eipBp detail.
 */
@Getter
@Setter
@ToString
public class EipBpDetailResponse extends AbstractBceResponse {
    /**
     * The name of eipBp.
     */
    private String name;

    /**
     * The id of eipBp.
     */
    private String id;

    /**
     * The type of eipBp binding.'eip' or 'eipGroup'.
     */
    private String bindType;

    /**
     * The bandwidthInMbps of eipBp.
     */
    private Integer bandwidthInMbps;

    /**
     * The instanceId of eipBp binding.
     */
    private String instanceId;

    /**
     * The ips of eipBp binding. If bindType is eip,this param contains only one ip address,
     * else contains all ip address in eipGroup.
     */
    private List<String> eips;

    /**
     * The instance's bandwidthInMbps of eipBp binding.
     */
    private Integer instanceBandwidthInMbps;

    /**
     * The eipBp createTime. UTC format.
     */
    private String createTime;

    /**
     * The eipBp autoReleaseTime. UTC format. If not set, it is the same as the expiration time of the bound resource.
     */
    private String autoReleaseTime;

    /**
     * The tags bound to eipBp.
     */
    private List<TagModel> tags;
}
