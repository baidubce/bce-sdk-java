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

import com.baidubce.auth.BceCredentials;

import java.util.List;

/**
 * Request object containing all the options for setting a Object's Access Control List (ACL).
 */
public class SetObjectAclRequest extends GenericObjectRequest {

    /**
     * For versioning
     */
    private String versionId;

    /**
     * The json format for Object Acl.
     */
    private String jsonObjectAcl;

    /**
     * The accessControlList of this specified Object.
     */
    private List<Grant> accessControlList;

    /**
     * The canned ACL to apply to the specified Object.
     */
    private CannedAccessControlList cannedAcl;

    /**
     * The xBceGrantRead apply to the specified Object.
     */
    private String xBceGrantRead;

    /**
     * The xBceGrantFullControl apply to the specified Object.
     */
    private String xBceGrantFullControl;

    /**
     * Constructs a void Constructor for SetObjectAclRequest.
     */
    public SetObjectAclRequest() {

    }

    /**
     * Constructs a new SetObjectAclRequest object, ready to set the specified
     * jsonObjectAcl on the specified bucket/object when this request is executed.
     * @param bucketName The name of the bucket whose Object will be set Acl by this request.
     * @param key The name of the Object whose ACL will be set by this request.
     * @param jsonObjectAcl The json style of acl to apply to the specified object when this request is executed.
     */
    public SetObjectAclRequest(String bucketName, String key, String jsonObjectAcl) {
        super(bucketName, key);
        this.jsonObjectAcl = jsonObjectAcl;
    }

    /**
     * Constructs a new SetObjectAclRequest object, ready to set the specified
     * ACL on the specified bucket/object when this request is executed.
     * @param bucketName The name of the bucket whose Object acl will be set by this request.
     * @param key The name of the Object whose ACL will be set by this request.
     * @param accessControlList The custom Access Control List containing the access rules to,
     * apply to the specified bucket/object when this request is executed.
     */
    public SetObjectAclRequest(String bucketName, String key, List<Grant> accessControlList) {
        super(bucketName, key);
        this.accessControlList = accessControlList;
    }

    /**
     * Constructs a new SetObjectAclRequest object, ready to set the specified,
     * canned ACL on the specified bucket/object when this request is executed.
     * @param bucketName The name of the bucket whose Object acl will be set by this request.
     * @param key The name of the Object whose ACL will be set by this request.
     * @param cannedAcl The Canned Access Control List to apply to the specified,
     * bucket/object when this request is executed.
     */
    public SetObjectAclRequest(String bucketName, String key, CannedAccessControlList cannedAcl) {
        super(bucketName, key);
        this.cannedAcl = cannedAcl;
    }

    @Override
    public SetObjectAclRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public SetObjectAclRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    @Override
    public SetObjectAclRequest withKey(String key) {
        this.setKey(key);
        return this;
    }

    /**
     * Gets the json format of Object Acl.
     * @return the json format of Object Acl.
     */
    public String getJsonObjectAcl() {
        return jsonObjectAcl;
    }

    /**
     * Sets the json format of Object Acl.
     * @param jsonObjectAcl The json format of Object Acl.
     */
    public void setJsonObjectAcl(String jsonObjectAcl) {
        this.jsonObjectAcl = jsonObjectAcl;
    }

    /**
     * Gets the accessControlList of Object Acl.
     * @return the accessControlList of Object Acl.
     */
    public List<Grant> getAccessControlList() {
        return accessControlList;
    }

    /**
     * Sets the accessControlList of Object Acl.
     * @param accessControlList The accessControlList of Object Acl.
     */
    public void setAccessControlList(List<Grant> accessControlList) {
        this.accessControlList = accessControlList;
    }

    /**
     * Gets the cannedAcl of Object Acl.
     * @return the cannedAcl of Object Acl.
     */
    public CannedAccessControlList getCannedAcl() {
        return cannedAcl;
    }

    /**
     * Sets the cannedAcl of Object Acl.
     * @param cannedAcl The cannedAcl of Object Acl.
     */
    public void setCannedAcl(CannedAccessControlList cannedAcl) {
        this.cannedAcl = cannedAcl;
    }

    /**
     * Gets the xBceGrantRead of Object Acl.
     * @return the xBceGrantRead of Object Acl.
     */
    public String getxBceGrantRead() {
        return xBceGrantRead;
    }

    /**
     * Sets the xBceGrantRead of Object Acl.
     * @param xBceGrantRead The xBceGrantRead of Object Acl.
     */
    public void setxBceGrantRead(String xBceGrantRead) {
        this.xBceGrantRead = xBceGrantRead;
    }

    /**
     * Gets the xBceGrantFullControl of Object Acl.
     * @return the xBceGrantFullControl of Object Acl.
     */
    public String getxBceGrantFullControl() {
        return xBceGrantFullControl;
    }

    /**
     * Sets the xBceGrantFullControl of Object Acl.
     * @param xBceGrantFullControl The xBceGrantRead of Object Acl.
     */
    public void setxBceGrantFullControl(String xBceGrantFullControl) {
        this.xBceGrantFullControl = xBceGrantFullControl;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

}