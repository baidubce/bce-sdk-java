/*
 * Copyright (c) 2020 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bec.model.vm;

import com.baidubce.services.bec.model.enums.DiskTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Volume config information.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VolumeConfig {

    /**
     * The name of the volume.
     */
    private String name;

    /**
     * The type of the volume.
     */
    private DiskTypeEnum volumeType;

    /**
     * The size of the volume.
     */
    private Integer sizeInGB;

    /**
     * The pass through code of the volume.
     * Monopolized disk needs.
     */
    private String passthroughCode;

    /**
     * The pvc name.
     */
    private String pvcName;
}
