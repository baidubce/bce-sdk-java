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
 * @date 2021/6/17
 * @desc the object update the owner info about the domain.
 */
public class UpdateOwnerRequest extends AbstractBceRequest {

    private String domain;

    private String userType;

    private String ownerChinese;

    private String ownerEnglish;

    private String contactChinese;

    private String contactEnglish;

    private String email;

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

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("domain", domain)
                .add("userType", userType)
                .add("ownerChinese", ownerChinese)
                .add("ownerEnglish", ownerEnglish)
                .add("contactChinese", contactChinese)
                .add("contactEnglish", contactEnglish)
                .add("email", email)
                .toString();
    }
}
