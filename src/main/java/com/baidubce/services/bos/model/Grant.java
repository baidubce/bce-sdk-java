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
 * Specifies a grant, consisting of one grantee and one permission.
 */
public class Grant {

    /**
     * grantee of Bucket Acl.
     * Identify authorized person.
     */
    private List<Grantee> grantee;

    /**
     * permission of Bucket Acl.
     * Permissions affected by ACL configuration items.
     */
    private List<Permission> permission;

    /**
     * condtion of Bucket Acl.
     * Limits Contained in ACL Configuration Items, Supporting Configuration of IP Address and Referrer List.
     */
    private Condition condition;

    /**
     * resources of Bucket Acl.
     * Resources Affected by ACL Configuration Items.
     * Indicates to set access to the resources specified by resource.
     */
    private List<String> resource;

    /**
     * notResources of Bucket Acl.
     * Resources Affected by ACL Configuration Items.
     * Indicates setting access to a resource except the one specified by notResource.
     */
    private List<String> notResource;

    /**
     * Gets the grantee being granted a permission by this grant.
     * @return The grantee being granted a permission by this grant.
     */
    public List<Grantee> getGrantee() {
        return grantee;
    }

    /**
     * Sets the grantee being granted a permission by this grant.
     * @param grantee The grantee being granted a permission by this grant.
     */
    public void setGrantee(List<Grantee> grantee) {
        this.grantee = grantee;
    }

    /**
     * Sets the grantee being granted a permission by this grant.
     * @param grantee The grantee being granted a permission by this grant.
     * @return this object.
     */
    public Grant withGrantee(List<Grantee> grantee) {
        this.setGrantee(grantee);
        return this;
    }

    /**
     * Gets the permission being granted to the grantee by this grant.
     * @return The permission being granted to the grantee by this grant.
     */
    public List<Permission> getPermission() {
        return permission;
    }

    /**
     * Sets the permission being granted to the grantee by this grant.
     * @param permission The permission being granted to the grantee by this grant.
     */
    public void setPermission(List<Permission> permission) {
        this.permission = permission;
    }

    /**
     * Sets the permission being granted to the grantee by this grant.
     * @param permission The permission being granted to the grantee by this grant.
     * @return this object
     */
    public Grant withPermission(List<Permission> permission) {
        this.setPermission(permission);
        return this;
    }

    /**
     * Gets the condition of Bucket Acl.
     * @return the condition of Bucket Acl.
     */
    public Condition getCondition() {
        return condition;
    }

    /**
     * Sets the condition of Bucket Acl.
     * @param condition The condtion of Bucket Acl.
     */
    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    /**
     * Sets the condtion of Bucket Acl.
     * @param condition The condtion of Bucket Acl.
     * @return this object.
     */
    public Grant withCondition(Condition condition) {
        this.setCondition(condition);
        return this;
    }

    /**
     * Gets the resource of Bucket Acl.
     * @return the resource of Bucket Acl.
     */
    public List<String> getResource() {
        return resource;
    }

    /**
     * Sets the resource of Bucket Acl.
     * @param resource The resource of Bucket Acl.
     */
    public void setResource(List<String> resource) {
        this.resource = resource;
    }

    /**
     * Sets the resource of Bucket Acl.
     * @param resource The resource of Bucket Acl.
     * @return this object.
     */
    public Grant withResource(List<String> resource) {
        this.setResource(resource);
        return this;
    }

    /**
     * Gets notResource of Bucket Acl.
     * @return the notResource if Bucket Acl.
     */
    public List<String> getNotResource() {
        return notResource;
    }

    /**
     * Sets notResource of Bucket Acl.
     * @param notResource The notResource if Bucket Acl.
     */
    public void setNotResource(List<String> notResource) {
        this.notResource = notResource;
    }

    /**
     * Sets notResource of Bucket Acl.
     * @param notResource The notResource of Bucket Acl.
     * @return this object.
     */
    public Grant withNotResource(List<String> notResource) {
        this.setNotResource(notResource);
        return this;
    }

    /**
     * Constructs a new Grant object using the specified grantee and permission objects.
     *
     * @param grantee The grantee being granted a permission by this grant.
     * @param permission The permission being granted to the grantee by this grant.
     */
    public Grant(List<Grantee> grantee, List<Permission> permission) {
        this.grantee = grantee;
        this.permission = permission;
    }

    /**
     * Constructs a void Constructor for Bucket Acl Grant.
     */
    public Grant() {
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.grantee == null) ? 0 : this.grantee.hashCode());
        result = prime * result + ((this.permission == null) ? 0 : this.permission.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Grant other = (Grant) obj;
        if (this.grantee == null) {
            if (other.grantee != null) {
                return false;
            }
        } else if (!this.grantee.equals(other.grantee)) {
            return false;
        }
        if (this.permission != other.permission) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Grant{"
                + "grantee=" + grantee
                + ", permission=" + permission
                + ", condition=" + condition
                + ", resource=" + resource
                + ", notResource=" + notResource
                + '}';
    }
}
