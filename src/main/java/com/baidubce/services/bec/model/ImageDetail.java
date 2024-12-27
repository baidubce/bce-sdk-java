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
package com.baidubce.services.bec.model;

import lombok.Data;

/**
 * Image detail.
 */
@Data
public class ImageDetail {

    /**
     * Id.
     */
    private String id;

    /**
     * The id of image.
     */
    private String imageId;

    /**
     * The name of image.
     */
    private String name;

    /**
     * nameFri.
     */
    private String nameFri;

    /**
     * The type of image.
     */
    private String imageType;

    /**
     * The id of snapshot.
     */
    private String snapshotId;

    /**
     * The number of cpu.
     */
    private Integer cpu;

    /**
     * The number of memory.
     */
    private Integer memory;

    /**
     * The type of os.
     */
    private String osType;

    /**
     * The version of os.
     */
    private String osVersion;

    /**
     * The name of os.
     */
    private String osName;

    /**
     * Os creation time.
     */
    private String osBuild;

    /**
     * Os language.
     */
    private String osLang;

    /**
     * Disk size.
     */
    private Integer diskSize;

    /**
     * Creation time.
     */
    private String createTime;

    /**
     * Status.
     */
    private String status;

    /**
     * The minimum amount of memory.
     */
    private Integer minMem;

    /**
     * The minimum number of CPUs.
     */
    private Integer minCpu;

    /**
     * The minimum number of disks.
     */
    private Integer minDiskGb;

    /**
     * Description.
     */
    private String desc;

    /**
     * osArch.
     */
    private String osArch;

    /**
     * ephemeralSize.
     */
    private Integer ephemeralSize;

    /**
     * Image description.
     */
    private String imageDescription;

    /**
     * Share limit.
     */
    private Integer shareToUserNumLimit;

    /**
     * Number of shares.
     */
    private Integer sharedToUserNum;

    /**
     * fpgaType.
     */
    private String fpgaType;
}
