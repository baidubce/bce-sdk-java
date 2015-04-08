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
package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

import java.util.List;

/**
 * Request object containing all the options for setting a bucket's Access Control List (ACL).
 */
public class SetBucketAclRequest extends GenericBucketRequest {

    /**
     * The canned ACL to apply to the specified bucket.
     */
    private CannedAccessControlList cannedAcl;

    /**
     * The accessControlList of this specified bucket.
     */
    private List<Grant> accessControlList;

    /**
     * Constructs a new SetBucketAclRequest object, ready to set the specified
     * canned ACL on the specified bucket when this request is executed.
     *
     * @param bucketName The name of the bucket whose ACL will be set by this request.
     * @param cannedAcl  The Canned Access Control List to apply to the specified
     *                   bucket when this request is executed.
     */
    public SetBucketAclRequest(String bucketName, CannedAccessControlList cannedAcl) {
        super(bucketName);
        this.setCannedAcl(cannedAcl);
    }

    /**
     * Constructs a new SetBucketAclRequest object, ready to set the specified
     * ACL on the specified bucket when this request is executed.
     *
     * @param bucketName The name of the bucket whose ACL will be set by this request.
     * @param accessControlList The custom Access Control List containing the access rules to
     *            apply to the specified bucket when this request is executed.
     */
    public SetBucketAclRequest(String bucketName, List<Grant> accessControlList) {
        super(bucketName);
        this.setAccessControlList(accessControlList);
    }

    @Override
    public SetBucketAclRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public SetBucketAclRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    /**
     * Returns the canned ACL to be applied to the specified bucket when this
     * request is executed.
     *
     * @return The canned ACL to be applied to the specified bucket when this
     * request is executed.
     */
    public CannedAccessControlList getCannedAcl() {
        return this.cannedAcl;
    }

    /**
     * Sets the canned ACL to be applied to the specified bucket when this
     * request is executed.
     *
     * @param cannedAcl The canned ACL to be applied to the specified bucket when this
     *                  request is executed.
     */
    public void setCannedAcl(CannedAccessControlList cannedAcl) {
        this.cannedAcl = cannedAcl;
    }

    /**
     * Sets the canned ACL to be applied to the specified bucket when this
     * request is executed.
     *
     * @param cannedAcl The canned ACL to be applied to the specified bucket when this
     *                  request is executed.
     */
    public SetBucketAclRequest withCannedAcl(CannedAccessControlList cannedAcl) {
        this.setCannedAcl(cannedAcl);
        return this;
    }

    /**
     * Returns the custom ACL to be applied to the specified bucket when this
     * request is executed. A request can use either a custom ACL or a canned
     * ACL, but not both.
     *
     * @return The custom ACL to be applied to the specified bucket when this
     *         request is executed.
     */
    public List<Grant> getAccessControlList() {
        return accessControlList;
    }

    /**
     * Sets the custom Access Control List containing the access rules to
     * apply to the specified bucket when this request is executed.
     *
     * @param accessControlList The custom Access Control List containing the access rules to
     *            apply to the specified bucket when this request is executed.
     */
    public void setAccessControlList(List<Grant> accessControlList) {
        this.accessControlList = accessControlList;
    }

    /**
     * Sets the custom Access Control List containing the access rules to
     * apply to the specified bucket when this request is executed.
     *
     * @param accessControlList The custom Access Control List containing the access rules to
     *            apply to the specified bucket when this request is executed.
     */
    public SetBucketAclRequest withAccessControlList(List<Grant> accessControlList) {
        this.setAccessControlList(accessControlList);
        return this;
    }
}
