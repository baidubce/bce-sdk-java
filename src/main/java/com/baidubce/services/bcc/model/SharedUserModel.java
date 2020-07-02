/*
 * Copyright (c) 2019-2020 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bcc.model;

/**
 * The detail model of the sharedUser.
 */
public class SharedUserModel {

    /**
     * The name of account for sharing image
     */
    private String account;

    /**
     * The id of account for sharing image
     */
    private String accountId;

    /**
     * The account of uc for sharing image
     */
    private String ucAccount;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUcAccount() {
        return ucAccount;
    }

    public void setUcAccount(String ucAccount) {
        this.ucAccount = ucAccount;
    }

    @Override
    public String toString() {
        return "SharedUserModel{"
                + "account='" + account + '\''
                + ", accountId='" + accountId + '\''
                + ", ucAccount='" + ucAccount + '\''
                + '}';
    }
}
