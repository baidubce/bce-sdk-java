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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @Author zhangyongchao01
 * @Since 2024-11-15 17:00
 * @Version v1.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VmImageVo {

    /**
     * The image id.
     */
    private String imageId;

    /**
     * Whether the image is used.
     */
    private Boolean beingUsed;

    /**
     * The image status, like IMAGING，SUCCEEDED.
     */
    private String status;

    /**
     * The bcc Base-line image id.
     */
    private String bccImageId;

    /**
     * The image name.
     */
    private String name;

    /**
     * The image type.
     */
    private String imageType;

    /**
     * The os type.
     */
    private String osType;

    /**
     * The os version.
     */
    private String osVersion;

    /**
     * The os name.
     */
    private String osName;

    /**
     * The os build version.
     */
    private String osBuild;

    /**
     * The os language.
     */
    private String osLang;

    /**
     * The os arch.
     */
    private String osArch;

    /**
     * The image size in byte.
     */
    private Long sizeInByte;
}