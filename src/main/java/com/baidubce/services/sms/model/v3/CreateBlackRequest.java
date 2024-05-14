/*
 * Copyright (c) 2020 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.sms.model.v3;

import com.baidubce.services.sms.model.SmsRequest;

public class CreateBlackRequest extends SmsRequest {

    /**
     * type of black
     * The value of type could be MerchantBlack or SignatureBlack
     */
    private String type;

    /**
     * mobile of black
     * Support multiple mobile phone numbers, up to 200 maximum, separated by comma.
     */
    private String phone;

    /**
     * countryType of black mobile
     * The value of type could be DOMESTIC or INTERNATIONAL
     */
    private String countryType;

    /**
     * string-based Signature ID
     * When the value of "type" is "SignatureBlack", this field is required.
     */
    private String signatureIdStr;

    /**
     * smsType
     * When the value of "type" is "SignatureBlack", this field is required.
     */
    private String smsType;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSignatureIdStr() {
        return signatureIdStr;
    }

    public void setSignatureIdStr(String signatureIdStr) {
        this.signatureIdStr = signatureIdStr;
    }

    public String getSmsType() {
        return smsType;
    }

    public void setSmsType(String smsType) {
        this.smsType = smsType;
    }

    public String getCountryType() {
        return countryType;
    }

    public void setCountryType(String countryType) {
        this.countryType = countryType;
    }
}
