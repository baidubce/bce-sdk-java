/*
 * Copyright 2018 Baidu, Inc.
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
package com.baidubce.services.kms.model;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * Master key information.
 */
public class KeyMetadata {
    /**
     * It is masterKeyId
     */ 
    private String keyId = null;

    /**
     * the time that create master key
     */ 
    private Date creationDate;

    /**
     * The state of master key
     */ 
    private String keyState = null;

    /**
     * The description of master key
     */ 
    private String description = null;

    /**
     * The deletion date of master key
     */ 
    private String deletionDate = null;
    
    /**
     * The Usage of master key
     */ 
    private String keyUsage = null;

    private String region = null;

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setCreationDate(String creationDate) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
            this.creationDate = formatter.parse(creationDate.replaceAll("Z$", "+0000"));
        } catch (ParseException e) {
            this.creationDate = new Date();
            e.printStackTrace();
        }
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setKeyState(String keyState) {
        this.keyState = keyState;
    }

    public String getKeyState() {
        return keyState;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setKeyUsage(String keyUsage) {
        this.keyUsage = keyUsage;
    }
    public String getKeyUsage() {
        return keyUsage;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegion() {
        return region;
    }

    public void setDeletionDate(String deletionDate) {
        this.deletionDate = deletionDate;
    }

    public String getDeletionDate() {
        return deletionDate;
    }
}
// vim: et tw=100 ts=4 sw=4 cc=120
