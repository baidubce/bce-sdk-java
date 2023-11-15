/*
 * Copyright (c) 2023 Baidu.com, Inc. All Rights Reserved
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

package com.baidubce.services.bci.model.common;

/**
 * The http header of http action
 */
public class HTTPHeader {

    /**
     * The name of http header
     */
    private String name;

    /**
     * The value of http header
     */
    private String value;

    /**
     * Construct a new HTTPHeader object.
     */
    public HTTPHeader() {

    }

    /**
     * Construct a new HTTPHeader object.
     *
     * @param name  The name of http header
     * @param value The value of http header
     */
    public HTTPHeader(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public HTTPHeader setName(String name) {
        this.name = name;
        return this;
    }

    public String getValue() {
        return value;
    }

    public HTTPHeader setValue(String value) {
        this.value = value;
        return this;
    }
}