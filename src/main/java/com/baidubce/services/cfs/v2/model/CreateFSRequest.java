/*
 * Copyright 2022 Baidu, Inc. All Rights Reserved
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
package com.baidubce.services.cfs.v2.model;

import com.baidubce.common.BaseBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateFSRequest extends BaseBceRequest {
    /**
     * FileSystem的名称，方便记忆。长度1~65个字节，字母开头，可包含字母数字-_/.字符。
     */
    private String fsName;

    /**
     * 文件系统类型：1.cap（性能型），默认性能型
     */
    private String type;

    /**
     * 协议类型：1.nfs 2.smb，默认nfs协议
     */
    private String protocal;

    /**
     * vpcId
     */
    private String vpcId;

    /**
     * zone
     */
    private String zone;

    public void setFsName(String fsName) {
        this.fsName = fsName;
    }

    public String getFsName() {
        return this.fsName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setProtocal(String protocal) {
        this.protocal = protocal;
    }

    public String getProtocal() {
        return this.protocal;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    public String getVpcId() {
        return this.vpcId;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getZone() {
        return this.zone;
    }

    @Override
    public String toString() {
        return "CreateFSRequest{"
                + "fsName=" + fsName + "\n"
                + "type=" + type + "\n"
                + "protocal=" + protocal + "\n"
                + "vpcId=" + vpcId + "\n"
                + "zone=" + zone + "\n"
                + "}";
    }

}