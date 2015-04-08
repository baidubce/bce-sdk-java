/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
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
 * The model object of email detail. It is contained by @link{GetVerifiedEmailResponse} and @link{ListVerifiedEmailResponse}
 */
public class EmailDetailModel {
    /**
     * The email address
     */
    private String address;
    /**
     * The status of verified
     * <p>
     * The value range of status is between 0 and 5
     */
    private Integer status;

    public EmailDetailModel() {
    }

    public EmailDetailModel(String address, Integer status) {
        this.address = address;
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EmailDetailModel [address=" + address + ", status=" + status + "]";
    }

}