/*
 * Copyright (c) 2024 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bec.model.enums;

/**
 * @Author zhangyongchao01
 * @Since 2024-11-14 11:45
 * @Version v1.0
 * The enum of dns type.
 */
public enum DnsTypeEnum {
    /**
     * none.
     */
    NONE(""),

    /**
     * default.
     */
    DEFAULT("default"),

    /**
     * local.
     */
    LOCAL("local"),

    /**
     * customize.
     */
    CUSTOMIZE("customize");

    private final String value;

    DnsTypeEnum(String val) {
        this.value = val;
    }

    public String getValue() {
        return this.value;
    }
}