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
    private int cpu;

    /**
     * The number of memory.
     */
    private int memory;

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
    private int diskSize;

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
    private int minMem;

    /**
     * The minimum number of CPUs.
     */
    private int minCpu;

    /**
     * The minimum number of disks.
     */
    private int minDiskGb;

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
    private int ephemeralSize;

    /**
     * Image description.
     */
    private String imageDescription;

    /**
     * Share limit.
     */
    private int shareToUserNumLimit;

    /**
     * Number of shares.
     */
    private int sharedToUserNum;

    /**
     * fpgaType.
     */
    private String fpgaType;
}
