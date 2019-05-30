/*
 * Copyright (c) 2018 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.dumap;

/**
 * DuMap validate message.
 *
 * @author weizhijun
 * @date 2018/10/16
 */
public interface DuMapValidateMsg {
    public static final String VALIDATE_MESSAGE_APP_ID = "appId不能为空";
    public static final String VALIDATE_MESSAGE_SEARCH_KEYWORD = "检索关键字不能为空";
    public static final String VALIDATE_MESSAGE_SEARCH_AREA = "检索区域不能为空";
    public static final String VALIDATE_MESSAGE_POI_UID = "poi的uid不能为空";
    public static final String VALIDATE_MESSAGE_POI_SCOPE = "检索结果详细程度不能为空，可取值1、2";
    public static final String VALIDATE_MESSAGE_ADDRESS = "解析地址不能为空";
    public static final String VALIDATE_MESSAGE_ORIGIN = "起点坐标不能为空";
    public static final String VALIDATE_MESSAGE_DESTINATION = "终点坐标不能为空";
    public static final String VALIDATE_MESSAGE_COORDS = "源坐标不能为空";
    public static final String VALIDATE_MESSAGE_SRC = "厂商标识不能为空";
    public static final String VALIDATE_MESSAGE_PROD = "产品线名称不能为空";
    public static final String VALIDATE_MESSAGE_VER = "服务版本号不能为空";
}
