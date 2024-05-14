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
package com.baidubce.services.eipgroup.model;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bcc.model.TagModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Eip group model.
 */
@Getter
@Setter
@ToString
public class GetEipGroupResponse extends AbstractBceResponse {

    /**
     * The id of EipGroup.
     */
    private String id;

    /**
     * The name of EipGroup.
     */
    private String name;

    /**
     * The eips of EipGroup.
     */
    private List<Eip> eips;

    /**
     * The status of EipGroup.
     */
    private String status;

    /**
     * The bandwidthInMbps of EipGroup.
     */
    private int bandwidthInMbps;

    /**
     * The paymentTiming of EipGroup.
     */
    private String paymentTiming;

    /**
     * The createTime of EipGroup.
     */
    private String createTime;

    /**
     * The expireTime of EipGroup.
     */
    private String expireTime;

    /**
     * The tags bound to eipGroup.
     */
    private List<TagModel> tags;
}
