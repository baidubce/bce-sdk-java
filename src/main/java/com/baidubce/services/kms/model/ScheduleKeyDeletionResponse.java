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
 * Contains the details returned from Kms after calling the ScheduleKeyDeletion operation.
 */
public class ScheduleKeyDeletionResponse extends KmsResponse {

    private Date deletionDate;

    private String keyId;

    public void setDeletionDate(String deletionDate) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
            this.deletionDate = formatter.parse(deletionDate.replaceAll("Z$", "+0000"));
        } catch (ParseException e) {
            this.deletionDate = new Date();
            e.printStackTrace();
        }
    }

    public Date getDeletionDate() {
        return deletionDate;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getKeyId() {
        return keyId;
    }
}
// vim: et tw=100 ts=4 sw=4 cc=120
