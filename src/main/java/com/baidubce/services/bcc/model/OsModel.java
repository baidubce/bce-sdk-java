/*
 * Copyright (c) 2019-2020 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bcc.model;

/**
 * The Specifications detail model for os.
 * See more detail on
 * <a href = "https://bce.baidu.com/doc/BCC/API.html#.E5.AE.9E.E4.BE.8B.E5.A5.97.E9.A4.90.E8.A7.84.E6.A0.BC">
 *     BCE API doc</a>
 */
public class OsModel {
    /**
     * short id of instance
     */
    private String instanceId;

    /**
     * arch of os, eg: x86_64 (64bit)
     */
    private String osArch;

    /**
     * name of os, eg:CentOS
     */
    private String osName;

    /**
     * version of os, eg:6.5
     */
    private String osVersion;

    /**
     * type of os, eg:linux
     */
    private String osType;

    /**
     * The language of the operating system. CHS means Chinese, ENG means English.
     */
    private String osLang;

    /**
     * The special version information of the operating system.
     */
    private String specialVersion;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getOsArch() {
        return osArch;
    }

    public void setOsArch(String osArch) {
        this.osArch = osArch;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public String getOsLang() {
        return osLang;
    }

    public void setOsLang(String osLang) {
        this.osLang = osLang;
    }

    public String getSpecialVersion() {
        return specialVersion;
    }

    public void setSpecialVersion(String specialVersion) {
        this.specialVersion = specialVersion;
    }

    @Override
    public String toString() {
        return "OsModel{"
                + "instanceId='" + instanceId + '\''
                + ", osArch='" + osArch + '\''
                + ", osName='" + osName + '\''
                + ", osVersion='" + osVersion + '\''
                + ", osType='" + osType + '\''
                + ", osLang='" + osLang + '\''
                + ", specialVersion='" + specialVersion + '\''
                + '}';
    }
}
