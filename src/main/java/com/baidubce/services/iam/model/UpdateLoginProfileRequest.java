/*
 * Copyright 2020 Baidu, Inc. All Rights Reserved
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
package com.baidubce.services.iam.model;

import com.baidubce.common.BaseBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateLoginProfileRequest extends BaseBceRequest {
    /**
     * password
     */
    private String password;

    /**
     * needResetPassword
     */
    private Boolean needResetPassword;

    /**
     * enabledLoginMfa
     */
    private Boolean enabledLoginMfa;

    /**
     * loginMfaType
     */
    private String loginMfaType;

    /**
     * thirdPartyType
     */
    private String thirdPartyType;

    /**
     * thirdPartyAccount
     */
    private String thirdPartyAccount;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setNeedResetPassword(Boolean needResetPassword) {
        this.needResetPassword = needResetPassword;
    }

    public Boolean isNeedResetPassword() {
        return this.needResetPassword;
    }

    public void setEnabledLoginMfa(Boolean enabledLoginMfa) {
        this.enabledLoginMfa = enabledLoginMfa;
    }

    public Boolean isEnabledLoginMfa() {
        return this.enabledLoginMfa;
    }

    public void setLoginMfaType(String loginMfaType) {
        this.loginMfaType = loginMfaType;
    }

    public String getLoginMfaType() {
        return this.loginMfaType;
    }

    public void setThirdPartyType(String thirdPartyType) {
        this.thirdPartyType = thirdPartyType;
    }

    public String getThirdPartyType() {
        return this.thirdPartyType;
    }

    public void setThirdPartyAccount(String thirdPartyAccount) {
        this.thirdPartyAccount = thirdPartyAccount;
    }

    public String getThirdPartyAccount() {
        return this.thirdPartyAccount;
    }

    @Override
    public String toString() {
        return "UpdateLoginProfileRequest{"
                + "password=" + password + "\n"
                + "needResetPassword=" + needResetPassword + "\n"
                + "enabledLoginMfa=" + enabledLoginMfa + "\n"
                + "loginMfaType=" + loginMfaType + "\n"
                + "thirdPartyType=" + thirdPartyType + "\n"
                + "thirdPartyAccount=" + thirdPartyAccount + "\n"
                + "}";
    }

}