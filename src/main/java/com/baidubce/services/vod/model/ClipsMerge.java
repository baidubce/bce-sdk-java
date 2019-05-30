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

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.Min;
import java.util.List;

/**
 * The advanced attributes of clips merge
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClipsMerge {

    /**
     * clips merge scope
     */
    private List<String> scope;

    /**
     * video list waiting to be merged
     */
    private List<ClipsMergeLocation> videoList;

    /**
     * The advanced attributes of videoList
     */
    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ClipsMergeLocation {
        /**
         * BOS bucket name
         */
        private String bucketName;

        /**
         * BOS object key
         */
        private String objectName;

        /**
         * start time in millisecond
         */
        @Min(0)
        private Integer startTimeInMillisecond;

        /**
         * duration time in millisecond
         */
        @Min(1)
        private Integer durationInMillisecond;
    }

}
