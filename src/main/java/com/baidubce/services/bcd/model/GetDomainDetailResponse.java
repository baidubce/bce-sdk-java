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

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Objects;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author yangzhensheng
 * @date 2021/6/18
 * @desc the detail infomation about the domain.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetDomainDetailResponse extends AbstractBceResponse {

    private String domain;
    private Timestamp registerTime;
    private Date expireTime;
    private long expireTimestamp;
    private String userType;
    private String contactId;
    private String ownerChinese;
    private String ownerEnglish;
    private String contactChinese;
    private String contactEnglish;
    private String email;
    private Region region;
    private String addressChinese;
    private String addressEnglish;
    private String postalCode;
    private String mobilePhone;
    private String countryCode;
    private String areaCode;
    private String phoneNumber;
    private Boolean privacy;
    private Boolean supportPrivacy;
    private String status;
    private String verifyStatus;
    private List<String> dns;
    private Boolean enableRecharge;
    private String noRechargeReason;
    private String namingStatus;

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public long getExpireTimestamp() {
        return expireTimestamp;
    }

    public void setExpireTimestamp(long expireTimestamp) {
        this.expireTimestamp = expireTimestamp;
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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
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

    public Boolean getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Boolean privacy) {
        this.privacy = privacy;
    }

    public Boolean getSupportPrivacy() {
        return supportPrivacy;
    }

    public void setSupportPrivacy(Boolean supportPrivacy) {
        this.supportPrivacy = supportPrivacy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public List<String> getDns() {
        return dns;
    }

    public void setDns(List<String> dns) {
        this.dns = dns;
    }

    public Boolean getEnableRecharge() {
        return enableRecharge;
    }

    public void setEnableRecharge(Boolean enableRecharge) {
        this.enableRecharge = enableRecharge;
    }

    public String getNoRechargeReason() {
        return noRechargeReason;
    }

    public void setNoRechargeReason(String noRechargeReason) {
        this.noRechargeReason = noRechargeReason;
    }

    public String getNamingStatus() {
        return namingStatus;
    }

    public void setNamingStatus(String namingStatus) {
        this.namingStatus = namingStatus;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("domain", domain)
                .add("registerTime", registerTime)
                .add("expireTime", expireTime)
                .add("expireTimestamp", expireTimestamp)
                .add("userType", userType)
                .add("ownerChinese", ownerChinese)
                .add("ownerEnglish", ownerEnglish)
                .add("contactChinese", contactChinese)
                .add("contactEnglish", contactEnglish)
                .add("email", email)
                .add("region", region)
                .add("addressChinese", addressChinese)
                .add("addressEnglish", addressEnglish)
                .add("postalCode", postalCode)
                .add("mobilePhone", mobilePhone)
                .add("countryCode", countryCode)
                .add("areaCode", areaCode)
                .add("phoneNumber", phoneNumber)
                .add("privacy", privacy)
                .add("supportPrivacy", supportPrivacy)
                .add("status", status)
                .add("verifyStatus", verifyStatus)
                .add("dns", dns)
                .add("enableRecharge", enableRecharge)
                .add("noRechargeReason", noRechargeReason)
                .add("namingStatus", namingStatus)
                .toString();
    }
}
