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
package com.baidubce.services.bsc.model.core;

/**
 * the model of bsc jar information
 */
public class BscJar {
    private Long id;

    private Long jobId;

    private String fileType;

    private String mainClass;

    private String region;

    private String bosBucket;

    private String bosObject;

    private String localFilename;

    private byte[] localJar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    public String getMainClass() {
        return mainClass;
    }

    public void setMainClass(String mainClass) {
        this.mainClass = mainClass == null ? null : mainClass.trim();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public String getBosBucket() {
        return bosBucket;
    }

    public void setBosBucket(String bosBucket) {
        this.bosBucket = bosBucket == null ? null : bosBucket.trim();
    }

    public String getBosObject() {
        return bosObject;
    }

    public void setBosObject(String bosObject) {
        this.bosObject = bosObject == null ? null : bosObject.trim();
    }

    public String getLocalFilename() {
        return localFilename;
    }

    public void setLocalFilename(String localFilename) {
        this.localFilename = localFilename == null ? null : localFilename.trim();
    }

    public byte[] getLocalJar() {
        return localJar;
    }

    public void setLocalJar(byte[] localJar) {
        this.localJar = localJar;
    }
}