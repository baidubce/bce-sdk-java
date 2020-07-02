/*
 * Copyright  2019 Baidu, Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.cfc.model;

import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request object for generating alias
 */
public abstract class GenericAliasRequest extends AbstractBceRequest {

    /**
     * The min length of alias name
     */
    private static final int MIN_ALIAS_LENGTH = 1;

    /**
     * The max length of alias name
     */
    private static final int MAX_ALIAS_LENGTH = 64;

    /**
     * Alias name, which can only be uppercase and lowercase letters, numbers, -, _, /, and special characters, and
     * must start with a letter. The maximum length is 64 characters.
     */
    @JsonProperty(value = "Name")
    private String Name;

    public GenericAliasRequest() {

    }

    public GenericAliasRequest(String name) {
        this.setName(name);
    }

    @JsonProperty(value = "Name")
    public String getName() {
        return this.Name;
    }

    public void setName(String name) {
        if (name == null) {
            this.Name = name;
            return;
        }
        name = name.trim();
        if (name.length() < MIN_ALIAS_LENGTH) {
            throw new IllegalArgumentException("Invalid name: " + name + ". " +
                    "name should not be less than " + MIN_ALIAS_LENGTH + ".");
        }

        if (name.length() > MAX_ALIAS_LENGTH) {
            throw new IllegalArgumentException("Invalid name: " + name + ". " +
                    "name should not be greater than " + MAX_ALIAS_LENGTH + ".");
        }

        if (!isLegalAlias(name)) {
            throw new IllegalArgumentException("Invalid name: " + name + ". " +
                    "name should be letters or digit or - or _ or / or . and the first character should be letter.");
        }
        this.Name = name;
    }

    private static boolean isLegalAlias(String alias) {
        String regex = "^[a-z0-9A-Z-_/.]+$";
        return alias.matches(regex) && isLetter(alias.charAt(0));
    }

    private static boolean isLetter(char ch) {
        return ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z';
    }

    public abstract GenericAliasRequest withName(String name);
}

