/*
 * Copyright 2020 Baidu, Inc.
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

import static com.google.common.base.Preconditions.checkNotNull;
import java.util.Map;
import com.baidubce.auth.BceCredentials;
import com.google.common.collect.Maps;

/**
 * Request object containing all the options for setting a object symlink.
 */
public class SetObjectSymlinkRequest extends GetObjectRequest {

    /**
     * The symlink of target object.
     */
    private String symlinkTarget;

    /**
     * Whether to overwrite the object with the same name when creating a soft link
     */
    private boolean forbidOverwrite;

    /**
     * The storageClass of symlink
     */
    private String storageClass;

    /**
     * Custom user metadata, represented in responses with the x-bce-meta- header prefix
     */
    private Map<String, String> userMetadata = Maps.newHashMap();

    public SetObjectSymlinkRequest(String bucketName, String key, String symlinkTarget) {
        super(bucketName, key);
        this.setSymlinkTarget(symlinkTarget);
    }

    /**
     * Gets the symlink of target object
     * @return
     */
    public String getSymlinkTarget() {
        return this.symlinkTarget;
    }

    /**
     * Sets the symlink of target object
     * @param symlinkTarget
     */
    public void setSymlinkTarget(String symlinkTarget) {
        checkNotNull(symlinkTarget, "symlinkTarget should not be null");
        this.symlinkTarget = symlinkTarget;
    }

    /**
     * Sets the symlink of target object,
     * and returns this object, enabling additional method calls to be chained together.
     */
    public SetObjectSymlinkRequest withSymlinkTarget(String symlinkTarget) {
        this.setSymlinkTarget(symlinkTarget);
        return this;
    }

    /**
     * Gets the flag of forbid overwrite
     * @return
     */
    public boolean isForbidOverwrite() {
        return this.forbidOverwrite;
    }

    /**
     * Set the flag of forbid overwrite
     * @param forbidOverwrite
     */
    public void setForbidOverwrite(boolean forbidOverwrite) {
        checkNotNull(forbidOverwrite, "forbidOverwrite should not be null");
        this.forbidOverwrite = forbidOverwrite;
    }

    /**
     * Sets the flag of forbid overwrite
     * and returns this object, enabling additional method calls to be chained together.
     */
    public SetObjectSymlinkRequest withForbidOverwrite(boolean forbidOverwrite) {
        this.setForbidOverwrite(forbidOverwrite);
        return this;
    }

    /**
     * Sets the symlink storageClass
     * @param storageClass
     */
    public void setStorageClass(String storageClass) {
        this.storageClass = storageClass;
    }

    /**
     * Gets the symlink storageClass
     * @return
     */
    public String getStorageClass() {
        return this.storageClass;
    }

    /**
     * Sets the symlink storageClass
     * and returns this object, enabling additional method calls to be chained together.
     * @param storageClass
     * @return
     */
    public SetObjectSymlinkRequest withStorageClass(String storageClass) {
        this.setStorageClass(storageClass);
        return this;
    }

    /**
     * Gets the user-meta of symlink
     * @return
     */
    public Map<String, String> getUserMetadata() {
        return userMetadata;
    }

    /**
     * Sets the user-meta of symlink
     * @param userMetadata
     */
    public void setUserMetadata(Map<String, String> userMetadata) {
        this.userMetadata = userMetadata;
    }

    /**
     * Sets user-meta of symlink
     * and returns this object, enabling additional method calls to be chained together.
     * @param userMetadata
     * @return
     */
    public SetObjectSymlinkRequest withUserMetadata(Map<String, String> userMetadata) {
        this.setUserMetadata(userMetadata);
        return this;
    }

    @Override
    public SetObjectSymlinkRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public SetObjectSymlinkRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    @Override
    public SetObjectSymlinkRequest withKey(String key) {
        this.setKey(key);
        return this;
    }
}