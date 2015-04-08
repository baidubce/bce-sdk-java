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
package com.baidubce.model;

/**
 * Represents the owner of an Baidu Bos bucket.
 */
public class User {
    // The owner id.
    private String id;
    // The display name of the owner.
    private String displayName;

    /**
     * Constructs a new user without specifying an ID or display name.
     */
    public User() {
    }

    /**
     * Constructs a new user with the specified ID and display name.
     *
     * @param id          The ID for the user.
     * @param displayName The display name for the user.
     */
    public User(String id, String displayName) {
        this.setId(id);
        this.setDisplayName(displayName);
    }

    /**
     * Gets the ID of the user.
     *
     * @return The ID of the user.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Sets the ID of the user.
     *
     * @param id The ID of the user.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Sets the ID of the user.
     *
     * @param id The ID of the user.
     */
    public User withId(String id) {
        this.setId(id);
        return this;
    }

    /**
     * Gets the display name of the user.
     *
     * @return The display name of the user.
     */
    public String getDisplayName() {
        return this.displayName;
    }

    /**
     * Sets the display name of the user.
     *
     * @param displayName The display name of the user.
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Sets the display name of the user.
     *
     * @param displayName The display name of the user.
     */
    public User withDisplayName(String displayName) {
        this.setDisplayName(displayName);
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.displayName == null) ? 0 : this.displayName.hashCode());
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
        User other = (User) obj;
        if (this.displayName == null) {
            if (other.displayName != null) {
                return false;
            }
        } else if (!this.displayName.equals(other.displayName)) {
            return false;
        }
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
        return "User [id=" + this.id + ", displayName=" + this.displayName + "]";
    }
}
