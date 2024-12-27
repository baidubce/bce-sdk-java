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
 * The enum of service provider.
 */
public enum ServiceProviderEnum {
    BGP("b", "BGP", "bgp"),

    CHINA_MOBILE("m", "移动", "cm"),

    CHINA_UNICOM("u", "联通", "un"),

    CHINA_TELECOM("t", "电信", "ct"),

    TRIPLE_LINE("l", "三线", "tl"),

    INTRA("", "内网", "intra");

    private final String value;

    private final String name;

    private final String abbr;

    public static final String ABBR_IX = "ix";

    ServiceProviderEnum(String value, String name, String abbr) {
        this.value = value;
        this.name = name;
        this.abbr = abbr;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String getAbbr() {
        return abbr;
    }
}