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
 * @Since 2024-11-08 13:55
 * @Version v1.0
 * <p>
 * The enum of route type.
 */
public enum RouteTypeEnum {

    BLACK_HOLE("blackhole", "黑洞路由", "blackhole", "blackhole"),

    CUSTOM("custom", "实例路由", "custom", "custom"),

    SYSTEM("system", "系统", "system", "sys"),

    TGW("vpc2tgw", "TGW路由", "vpc2tgw", "tgw"),

    HAVIP("havip", "高可用虚拟IP", "custom", "custom"),

    NAT("nat", "NAT网关", "nat", "nat");

    private final String value;

    private final String name;

    private final String bvsValue;

    private final String csnValue;

    RouteTypeEnum(String value, String name, String bvsValue, String csnValue) {
        this.name = name;
        this.value = value;
        this.bvsValue = bvsValue;
        this.csnValue = csnValue;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String getBvsValue() {
        return bvsValue;
    }

    public String getCsnValue() {
        return csnValue;
    }
}