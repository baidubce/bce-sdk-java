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

/**
 * notResource of Bucket Acl.
 * Resource Affected by ACL Configuration Items.
 * Indicates setting access to a resource except the one specified by notResource.
 */
public class NotResource {

    /**
     * the notResource of Bucket Acl
     */
    private List<String> notResource;

    /**
     * Gets the notResource of Bucket Acl.
     * @return the notResource of Bucket Acl.
     */
    public List<String> getNotResource() {
        return notResource;
    }

    /**
     * Sets the notResource of Bucket Acl.
     * @param notResource The resources of Bucket Acl.
     */
    public void setNotResource(List<String> notResource) {
        this.notResource = notResource;
    }

    /**
     * Sets the notResource of Bucket Acl.
     * @param notResource The resources of Bucket Acl.
     * @return this Object.
     */
    public NotResource withNotResource(List<String> notResource) {
        this.setNotResource(notResource);
        return this;
    }
}