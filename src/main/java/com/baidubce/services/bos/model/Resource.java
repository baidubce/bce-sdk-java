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
 * resources of Bucket Acl.
 * Resources Affected by ACL Configuration Items.
 * Indicates to set access to the resources specified by resource.
 */
public class Resource {

    /**
     * resources of Bucket Acl
     */
    private List<String> resource;

    /**
     * Constructs a void constructor for Resource.
     */
    public Resource() {
    }

    /**
     * Constructs a new Resource object for Resource.
     * @param rule
     */
    public Resource(List<String> resource) {
        this.resource = resource;
    }

    /**
     * Gets the resources of Bucket Acl.
     * @return the resources of Bucket Acl.
     */
    public List<String> getResource() {
        return resource;
    }

    /**
     * Sets the resources of Bucket Acl.
     * @param resource The resources of Bucket Acl.
     */
    public void setResource(List<String> resource) {
        this.resource = resource;
    }

    /**
     * Sets the resources of Bucket Acl.
     * @param resource The resources of Bucket Acl.
     * @return this Object.
     */
    public Resource withResource(List<String> resource) {
        this.setResource(resource);
        return this;
    }

    @Override
    public String toString() {
        return "Resource{"
                + "resource=" + resource.toString()
                + '}';
    }
}
