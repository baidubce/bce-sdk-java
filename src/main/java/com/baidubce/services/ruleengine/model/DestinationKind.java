/*
 * Copyright 2017 Baidu, Inc. All Rights Reserved.
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
package com.baidubce.services.ruleengine.model;

/**
 * Kinds of rule destination
 */
public class DestinationKind {
    public static final String MQTT = "MQTT";
    public static final String TSDB = "TSDB";
    public static final String KAFKA = "KAFKA";
    public static final String BOS = "BOS";
    public static final String MQTT_DYNAMIC = "MQTT_DYNAMIC";
    public static final String SMS = "SMS";
    public static final String FUNCTION = "FUNCTION";
    public static final String BIN2JSON = "BIN2JSON";
    public static final String RULE = "RULE";
    public static final String RDS = "RDS";
    public static final String BTS = "BTS";
}
