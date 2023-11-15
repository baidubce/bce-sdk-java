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

import com.baidubce.model.GenericAccountRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadOtaPackageRequest extends GenericAccountRequest {
    @NotNull
    private String fileName;
    @NotNull
    private String suffix;
    @NotNull
    private String showName;
    @NotNull
    private Boolean isEncrypted;
    @NotNull
    private String url;
    @NotNull
    private String md5;
    @NotNull
    private String sha1;
    @NotNull
    private System system;
    @NotNull
    private String fileSize;
    @NotNull
    private String dstVersion;
    private String label = null;
    private String packageType = "system";
    private int fileType = 0;
    private String fileSource = "user";
    private boolean isDiffPack = false;
    private String srcVersion = null;
    private String srcSha1 = null;
    private String packageName = null;

    public enum System {
        linux, android, rost, other
    }
}
