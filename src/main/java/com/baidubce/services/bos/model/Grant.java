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

import com.baidubce.model.User;

import java.util.List;

/**
 * Specifies a grant, consisting of one grantee and one permission.
 */
public class Grant {
    private List<Grantee> grantee;
    private List<Permission> permission;

    public Grant() {
    }

    /**
     * Constructs a new Grant object using the specified grantee and permission objects.
     *
     * @param grantee The grantee being granted a permission by this grant.
     * @param permission The permission being granted to the grantee by this grant.
     */
    public Grant(List<Grantee> grantee, List<Permission> permission) {
        this.setGrantee(grantee);
        this.setPermission(permission);
    }

    /**
     * Gets the grantee being granted a permission by this grant.
     *
     * @return The grantee being granted a permission by this grant.
     */
    public List<Grantee> getGrantee() {
        return this.grantee;
    }

    /**
     * Sets the grantee being granted a permission by this grant.
     *
     * @param grantee The grantee being granted a permission by this grant.
     */
    public void setGrantee(List<Grantee> grantee) {
        this.grantee = grantee;
    }

    /**
     * Sets the grantee being granted a permission by this grant.
     *
     * @param grantee The grantee being granted a permission by this grant.
     * @return this object
     */
    public Grant withGrantee(List<Grantee> grantee) {
        this.setGrantee(grantee);
        return this;
    }

    /**
     * Gets the permission being granted to the grantee by this grant.
     *
     * @return The permission being granted to the grantee by this grant.
     */
    public List<Permission> getPermission() {
        return this.permission;
    }

    /**
     * Sets the permission being granted to the grantee by this grant.
     *
     * @param permission The permission being granted to the grantee by this grant.
     */
    public void setPermission(List<Permission> permission) {
        this.permission = permission;
    }

    /**
     * Sets the permission being granted to the grantee by this grant.
     *
     * @param permission The permission being granted to the grantee by this grant.
     * @return this object
     */
    public Grant withPermission(List<Permission> permission) {
        this.setPermission(permission);
        return this;
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
        return "Grant [grantee=" + this.grantee + ", permission=" + this.permission + "]";
    }
}
