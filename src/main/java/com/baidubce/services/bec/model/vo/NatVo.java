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
package com.baidubce.services.bec.model.vo;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.tag.model.Tag;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author zhangyongchao01
 * @Since 2024-11-22 10:14
 * @Version v1.0
 * <p>
 * The nat view object.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NatVo extends AbstractBceResponse {

    /**
     * The bandwidth of nat.
     */
    private Integer bandwidth;

    /**
     * Creation time.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Date createTime;

    /**
     * The description.
     */
    private String desc;

    /**
     * The dnat eips.
     */
    private List<NatEipVo> dnatEips;

    /**
     * The eips.
     */
    private List<NatEipVo> eips;

    /**
     * The nat name.
     */
    private String name;

    /**
     * The nat id.
     */
    private String natId;

    /**
     * The region id.
     */
    private String regionId;

    /**
     * The specification.
     */
    private String spec;

    /**
     * The nat status, like starting/down/active/deleting
     */
    private String status;

    /**
     * The tags.
     */
    private List<Tag> tags;

    /**
     * The vpc id.
     */
    private String vpcId;
}