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
 * Represents the grantee of an Baidu Bos bucket.
 */
public class Grantee {
    private String id;

    /**
     * Constructs a new grantee without specifying an ID.
     */
    public Grantee() {
    }

    /**
     * Constructs a new grantee with the specified ID.
     *
     * @param id          The ID for the user.
     */
    public Grantee(String id) {
        this.setId(id);
    }

    /**
     * Gets the ID of the grantee.
     *
     * @return The ID of the grantee.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Sets the ID of the grantee.
     *
     * @param id The ID of the grantee.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Sets the ID of the grantee.
     *
     * @param id The ID of the grantee.
     */
    public Grantee withId(String id) {
        this.setId(id);
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
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
        Grantee other = (Grantee) obj;
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Grantee [id=" + this.id + "]";
    }
}

