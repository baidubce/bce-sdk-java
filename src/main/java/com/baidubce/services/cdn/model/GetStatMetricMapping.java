/*
 * Copyright 2016-2019 Baidu, Inc.
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

package com.baidubce.services.cdn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * Provide some mapping to metric query
 *
 */
public class GetStatMetricMapping {

    @JsonIgnore
    public static final BiMap<String, String> PROVINCE_MAP = HashBiMap.create();
    @JsonIgnore
    public static final BiMap<String, String> ISP_MAP = HashBiMap.create();

    /***
     * Init PROVINCE_MAP and ISP_MAP
     */
    static {
        PROVINCE_MAP.put("anhui", "安徽");
        PROVINCE_MAP.put("beijing", "北京");
        PROVINCE_MAP.put("chongqing", "重庆");
        PROVINCE_MAP.put("fujian", "福建");
        PROVINCE_MAP.put("gansu", "甘肃");
        PROVINCE_MAP.put("guangdong", "广东");
        PROVINCE_MAP.put("guangxi", "广西");
        PROVINCE_MAP.put("guizhou", "贵州");
        PROVINCE_MAP.put("henan", "河南");
        PROVINCE_MAP.put("hebei", "河北");
        PROVINCE_MAP.put("heilongjiang", "黑龙江");
        PROVINCE_MAP.put("hubei", "湖北");
        PROVINCE_MAP.put("hunan", "湖南");
        PROVINCE_MAP.put("jiangsu", "江苏");
        PROVINCE_MAP.put("jiangxi", "江西");
        PROVINCE_MAP.put("jilin", "吉林");
        PROVINCE_MAP.put("liaoning", "辽宁");
        PROVINCE_MAP.put("neimenggu", "内蒙古");
        PROVINCE_MAP.put("ningxia", "宁夏");
        PROVINCE_MAP.put("qinghai", "青海");
        PROVINCE_MAP.put("shandong", "山东");
        PROVINCE_MAP.put("shanghai", "上海");
        PROVINCE_MAP.put("shannxi", "陕西");
        PROVINCE_MAP.put("shanxi", "山西");
        PROVINCE_MAP.put("sichuan", "四川");
        PROVINCE_MAP.put("tianjin", "天津");
        PROVINCE_MAP.put("unknown", "未知");
        PROVINCE_MAP.put("xinjiang", "新疆");
        PROVINCE_MAP.put("xizang", "西藏");
        PROVINCE_MAP.put("yunnan", "云南");
        PROVINCE_MAP.put("zhejiang", "浙江");
        PROVINCE_MAP.put("xianggang", "香港");
        PROVINCE_MAP.put("aomen", "澳门");
        PROVINCE_MAP.put("taiwan", "台湾");
        PROVINCE_MAP.put("hainan", "海南");

        ISP_MAP.put("ct", "电信");
        ISP_MAP.put("cnc", "联通");
        ISP_MAP.put("cmnet", "移动");
        ISP_MAP.put("ce", "教育网");
        ISP_MAP.put("pbs", "鹏博士");
        ISP_MAP.put("oc", "广电");
        ISP_MAP.put("sjhl", "世纪互联");
        ISP_MAP.put("fdbn", "方正宽带");
        ISP_MAP.put("wasu", "华数");
        ISP_MAP.put("other", "其他");
    }
}
