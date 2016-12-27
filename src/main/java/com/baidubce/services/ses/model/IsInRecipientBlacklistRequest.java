/*
 * Copyright 2014 Baidu, Inc.
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
package com.baidubce.services.ses.model;

/**
 * Request object for check it is in recipient blacklist. This object contains one parameter be set.
 */
public class IsInRecipientBlacklistRequest extends SesRequest {
    /**
     * The URL of email address, which is to be checked.
     */
    private String emailAddress;

    public IsInRecipientBlacklistRequest withEmailAddress(String emailAddress) {
        setEmailAddress(emailAddress);
        return this;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "IsInRecipientBlacklistRequest [emailAddress=" + emailAddress + "]";
    }

}
