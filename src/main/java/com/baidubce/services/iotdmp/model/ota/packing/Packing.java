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
package com.baidubce.services.iotdmp.model.ota.packing;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Packing extends AbstractBceResponse {
    private int issueId;
    private int platform;
    private int type;
    private String stepName;
    private boolean hasNextStep;
    private int status;
    private String packageUrl;
    private String srcSha1;
    private int fileSize;
    private String fileMd5;
    private String fileSha1;
    private String submitTime;
    private String packageName;
    private String showName;
    private String dstVersion;
    private String srcVersion;
    private int fileType;
}
