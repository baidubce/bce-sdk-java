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

/**
 * Specifies constants defining a canned access control list.
 *
 * <p>
 * Canned access control lists are commonly used access control lists (ACL) that can be
 * used as a shortcut when applying an access control list to Baidu Bos buckets.
 * Only a few commonly used configurations are available, but they offer an alternative to manually creating a custom ACL.
 */
public enum CannedAccessControlList {
    /**
     * This is the default access control policy for any new buckets or objects.
     */
    Private("private"),

    /**
     * If this policy is used on an object, it can be read from a browser without
     * authentication.
     */
    PublicRead("public-read"),

    /**
     * This access policy is not recommended for general use.
     */
    PublicReadWrite("public-read-write");

    private final String cannedAclHeader;

    private CannedAccessControlList(String cannedAclHeader) {
        this.cannedAclHeader = cannedAclHeader;
    }

    @Override
    public String toString() {
        return this.cannedAclHeader;
    }

}
