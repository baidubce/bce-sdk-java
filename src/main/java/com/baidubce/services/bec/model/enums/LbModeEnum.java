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
 * @Since 2024-11-19 11:39
 * @Version v1.0
 * <p>
 * The enum of load balance mode/strategy.
 */
public enum LbModeEnum {

    wrr("RoundRobin"),

    minconn("LeastConnection"),

    srch("Hash");

    /**
     * The rule of dispatch.
     */
    private String value;

    public String getValue() {
        return value;
    }

    LbModeEnum(String value) {
        this.value = value;
    }
}