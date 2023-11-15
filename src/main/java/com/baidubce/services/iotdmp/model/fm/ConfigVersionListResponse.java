/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
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
package com.baidubce.services.iotdmp.model.fm;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigVersionListResponse extends AbstractBceResponse {
    private String configName;
    private String productKey;
    private String productName;
    private String description;
    private List<Result> result;


    @Data
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Result {
        private String configVersion;
        private String fileType;
        private Long fileSize;
        private String etag;
        private String fileName;
        private String createTime;
        private String updateTime;

        public Result(ProductConfigVersion v) {
            configVersion = v.getConfigVersion();
            fileType = v.getConfigFileType();
            fileSize = v.getConfigFileSize();
            etag = v.getConfigMd5();
            fileName = v.getFileUniqueId();
            createTime = v.getCreateTime();
            updateTime = v.getUpdateTime();
        }
    }

}
