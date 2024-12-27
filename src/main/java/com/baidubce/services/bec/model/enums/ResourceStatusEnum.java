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
 * @Since 2024-11-10 22:06
 * @Version v1.0
 * <p>
 * The enum of resource status.
 */
public enum ResourceStatusEnum {

    STARTING("启动中"),

    RUNNING("运行中"),

    PENDING("调度中"),

    EXCEPTION("异常"),

    FAILED("错误"),

    UNKNOWN("未知"),

    TERMINATED("中止"),

    WAITING("等待"),

    STOP("停止"),

    BINDING("绑定中"),

    STOPPING("关机中"),

    TERMINATING("终止中"),

    DELETING("删除中"),

    NORMAL("正常");

    private String value;

    ResourceStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}