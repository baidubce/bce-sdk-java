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
package com.baidubce.services.iotdmp.model.ota.packages;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListOtaPackageResponse extends AbstractBceResponse {
    private int total;
    private int page;
    private int perPage;
    private List<String> labels;
    private List<OtaPackageData> packages;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class OtaPackageData {
        private int id;
        private String packageType;
        private int fileType;
        private String fileName;
        private String showName;
        private String packageName;
        private Boolean isDiffPack;
        private Boolean isEncrypted;
        private String url;
        private String md5;
        private String sha1;
        private String srcSha1;
        private String insertTime;
        private String fileSource;
        private String uploadUser;
        private long fileSize;
        private String srcVersion;
        private String dstVersion;
        private List<String> label;
    }
}
