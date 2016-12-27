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
package com.baidubce.services.bos.model;

import java.util.List;

public class GetBucketAclResponse extends BosResponse {
    public static final int MAX_SUPPORTED_ACL_VERSION = 1;
    /**
     * The acl version..
     */
    private int version = 1;

    /**
     * The user of this specified bucket.
     */
    private Grantee owner;

    /**
     * The accessControlList of this specified bucket.
     */
    private List<Grant> accessControlList;

    /**
     * Gets the acl version.
     */
    public int getVersion() {
        return version;
    }

    /**
     * Sets the acl version.
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * Gets the user of this bucket.
     *
     * @return The user of this bucket.
     */
    public Grantee getOwner() {
        return this.owner;
    }

    /**
     * Sets the user of this bucket.
     *
     * @param owner The user of this bucket.
     */
    public void setOwner(Grantee owner) {
        this.owner = owner;
    }

    /**
     * Gets the accessControlList of this bucket.
     *
     * @return The accessControlList of this bucket.
     */
    public List<Grant> getAccessControlList() {
        return this.accessControlList;
    }

    /**
     * Sets the accessControlList of this bucket.
     *
     * @param accessControlList The accessControlList of this bucket.
     */
    public void setAccessControlList(List<Grant> accessControlList) {
        this.accessControlList = accessControlList;
    }

}
