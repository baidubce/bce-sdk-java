/*
 * Copyright (C) 2021 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.bcd.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.util.List;

public class RegisterDomainRequest extends AbstractBceRequest {
    private String domain;
    private String userType;
    private String ownerChinese;
    private String ownerEnglish;
    private String contactChinese;
    private String contactEnglish;
    private String email;
    private Integer years;
    private String addressChinese;
    private String addressEnglish;
    private String postalCode;
    private String mobilePhone;
    private String areaCode;
    private String phoneNumber;
    private Region region;
    private Boolean privacy;
    private List<String> dns;

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

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
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

    public Boolean getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Boolean privacy) {
        this.privacy = privacy;
    }

    public List<String> getDns() {
        return dns;
    }

    public void setDns(List<String> dns) {
        this.dns = dns;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public static class Region {
        private String province;
        private String city;

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }
}
