/*
 * Copyright 2019 Baidu, Inc.
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
package com.baidubce.services.vod.model;

import com.baidubce.services.media.model.Insert;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * The advanced attributes in media process
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActionAttributes {

    /**
     * The pixel of horizontal offset
     */
    private Integer horizontalOffsetInPixel;

    /**
     * The pixel of vertical offset
     */
    private Integer verticalOffsetInPixel;

    /**
     * The width of watermark
     */
    private Integer watermarkWidth;

    /**
     * The height of watermark
     */
    private Integer watermarkHeight;

    /**
     * The seconds of cutting prologue
     */
    private Integer prologueCuttingInSeconds;

    /**
     * The milliseconds of cutting prologue
     */
    private Integer prologueCuttingInMillisecond;

    /**
     * The milliseconds of cutting epilogue
     */
    private Integer epilogueCuttingInMillisecond;

    /**
     * The advanced attributes of clips merge
     */
    private ClipsMerge clipsMerge;

    /**
     * The advanced attributes of media inserts
     */
    private List<Insert> inserts;

}
