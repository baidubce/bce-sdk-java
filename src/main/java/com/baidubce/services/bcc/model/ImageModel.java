/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
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

import java.util.Date;

/**
 * The model of image detail information
 */
public class ImageModel {

    /**
     * The id of the image.
     */
    private String id;

    /**
     * The name of the image.
     */
    private String name;

    /**
     * The type of the image,
     * see more detail on <a href = "https://bce.baidu.com/doc/BCC/API.html#ImageType">BCE API doc</a>
     */
    private String type;

    /**
     * Type of the operating system used in the image,like linux,windows etc.
     */
    private String osType;

    /**
     * The version of the operating system used in the image.
     */
    private String osVersion;

    /**
     * The architecture of the operating system used in the image.
     */
    private String osArch;

    /**
     * The name of the operating system used in the image.
     */
    private String osName;

    /**
     * The built version of the operating system used in the image.
     */
    private String osBuild;

    /**
     * The create time of the image
     */
    private Date createTime;

    /**
     * The status of the image.
     * see more detail on <a href = "https://bce.baidu.com/doc/BCC/API.html#ImageStatus">BCE API doc</>
     */
    private String status;

    /**
     * The description of the image.
     */
    private String desc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
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

    public String getOsBuild() {
        return osBuild;
    }

    public void setOsBuild(String osBuild) {
        this.osBuild = osBuild;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "ImageModel{"
                + "id='" + id + '\''
                + ", name='" + name + '\''
                + ", type='" + type + '\''
                + ", osType='" + osType + '\''
                + ", osVersion='" + osVersion + '\''
                + ", osArch='" + osArch + '\''
                + ", osName='" + osName + '\''
                + ", osBuild='" + osBuild + '\''
                + ", createTime='" + createTime + '\''
                + ", status='" + status + '\''
                + ", desc='" + desc + '\''
                + '}';
    }
}
