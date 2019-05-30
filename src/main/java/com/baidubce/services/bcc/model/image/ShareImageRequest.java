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
package com.baidubce.services.bcc.model.image;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The request for share image.
 *
 */
public class ShareImageRequest extends AbstractBceRequest {

    /**
     * The id of image.
     */
    @JsonIgnore
    private String imageId;

    /**
     * The accout name of user
     */
    private String account;

    /**
     * The accout id of user
     */
    private String accountId;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

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

    /**
     * Configure imageId for the request.
     *
     * @param imageId The id of image.
     * @return ShareImageRequest with imageId.
     */
    public ShareImageRequest withImageId(String imageId) {
        this.imageId = imageId;
        return this;
    }

    /**
     * Configure account for the request.
     *
     * @param account The id of image.
     * @return ShareImageRequest with account.
     */
    public ShareImageRequest withAccount(String account) {
        this.account = account;
        return this;
    }

    /**
     * Configure accountId for the request.
     *
     * @param accountId The id of image.
     * @return ShareImageRequest with accountId.
     */
    public ShareImageRequest withAccountId(String accountId) {
        this.accountId = accountId;
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return ShareImageRequest with credentials.
     */
    @Override
    public ShareImageRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
