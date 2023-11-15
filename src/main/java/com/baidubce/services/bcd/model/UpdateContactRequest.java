/*
 * Copyright (c) 2021 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bcd.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * @author yangzhensheng
 * @date 2021/6/17
 * @desc the object update the contact info about the domain.
 */
public class UpdateContactRequest extends AbstractBceRequest {

    private String domain;

    private String userType;

    private String contactChinese;

    private String contactEnglish;

    private String email;

    private String addressChinese;

    private String addressEnglish;

    private String postalCode;

    private String mobilePhone;

    private String countryCode;

    private String areaCode;

    private String phoneNumber;

    private Region region;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getContactChinese() {
        return contactChinese;
    }

    public void setContactChinese(String contactChinese) {
        this.contactChinese = contactChinese;
    }

    public String getContactEnglish() {
        return contactEnglish;
    }

    public void setContactEnglish(String contactEnglish) {
        this.contactEnglish = contactEnglish;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddressChinese() {
        return addressChinese;
    }

    public void setAddressChinese(String addressChinese) {
        this.addressChinese = addressChinese;
    }

    public String getAddressEnglish() {
        return addressEnglish;
    }

    public void setAddressEnglish(String addressEnglish) {
        this.addressEnglish = addressEnglish;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
