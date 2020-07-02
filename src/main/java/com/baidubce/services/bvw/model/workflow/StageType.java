/*
 * Copyright 2019-2020 Baidu, Inc. All Rights Reserved.
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
package com.baidubce.services.bvw.model.workflow;

/**
 * The enum class to enumerate all the supported stage type in SDK.
 */
public enum StageType {

    /**
     * The stage of start.
     */
    START,

    /**
     * The stage of PUBLISH.
     */
    PUBLISH,

    /**
     * The stage of media info.
     */
    MEDIAINFO,

    /**
     * The stage of black border detect.
     */
    BLACK_BORDER_DETECT,

    /**
     * The stage of transcoding job in mct.
     */
    TRANSCODING,

    /**
     * The stage of thumbnail job in mct.
     */
    THUMBNAIL,

    /**
     * The stage of no transcoding job.
     */
    NO_TRANSCODING,

    /**
     * The stage of vcr job.
     */
    VCR,

    /**
     * The stage of vca job.
     */
    VCA

}
