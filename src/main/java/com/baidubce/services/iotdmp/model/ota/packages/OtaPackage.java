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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OtaPackage {
    private int id;
    private String packageType;
    private int filetype;
    private String filename;
    @JsonProperty("show_name")
    private String showName;
    @JsonProperty("package_name")
    private String packageName;
    @JsonProperty("isDiffPack")
    private Boolean isDiffPack;
    @JsonProperty("is_encrypted")
    private Boolean isEncrypted;
    private String url;
    private String md5;
    private String sha1;
    private String srcSha1;
    private String insertTime;
    private String fileSource;
    private String uploadUser;
    private long filesize;
    @JsonProperty("src_version")
    private String srcVersion;
    private List<String> label;
    private String version;
    private int updatetype;
}
