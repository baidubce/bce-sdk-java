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
import com.google.common.base.Objects;

/**
 * @author yangzhensheng
 * @date 2021/5/27
 *
 * @desc the request object contain some filed info to add or update a domain resolve record.
 */
public class ModifyTemplateInfoRequest extends AbstractBceRequest {

    /**
     * the templateId is must when the update action.
     */
    private String templateId;

    /**
     * the user type such as INDIVIDUAL or ENTERPRISE
     */
    private String userType;

    /**
     * the Chinese owner name
     */
    private String ownerChinese;

    /**
     * the Chinese owner's English name
     */
    private String ownerEnglish;

    /**
     * the Chinese contact name
     */
    private String contactChinese;

    /**
     * the Chinese contact's English name
     */
    private String contactEnglish;

    /**
     * the email
     */
    private String email;

    /**
     * Chinses address
     */
    private String addressChinese;

    /**
     * English address
     */
    private String addressEnglish;

    /**
     * the post code
     */
    private String postalCode;

    /**
     * the mobile phone number
     */
    private String mobilePhone;

    /**
     * the country code,such as the code of China is 086
     */
    private String countryCode;

    /**
     * the area code represent the place
     */
    private String areaCode;

    /**
     * the phone number
     */
    private String phoneNumber;

    private Region region;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getOwnerChinese() {
        return ownerChinese;
    }

    public void setOwnerChinese(String ownerChinese) {
        this.ownerChinese = ownerChinese;
    }

    public String getOwnerEnglish() {
        return ownerEnglish;
    }

    public void setOwnerEnglish(String ownerEnglish) {
        this.ownerEnglish = ownerEnglish;
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

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("templateId", templateId)
                .add("userType", userType)
                .add("ownerChinese", ownerChinese)
                .add("ownerEnglish", ownerEnglish)
                .add("contactChinese", contactChinese)
                .add("contactEnglish", contactEnglish)
                .add("email", email)
                .add("addressChinese", addressChinese)
                .add("addressEnglish", addressEnglish)
                .add("postalCode", postalCode)
                .add("mobilePhone", mobilePhone)
                .add("countryCode", countryCode)
                .add("areaCode", areaCode)
                .add("phoneNumber", phoneNumber)
                .add("region", region)
                .toString();
    }
}
