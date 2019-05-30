/*
 * Copyright 2019 Baidu, Inc.
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
package com.baidubce.services.bsc.model.constant;

/**
 * constants for bsc Endpoint
 */
public class EndpointConstants {
    // endpoint fields
    // 命名规范：“服务名_属性名”；全部使用小写
    // HDFS related
    public static String HDFS_KEYTAB = "hdfs_keytab";
    public static String HDFS_PRINCIPAL = "hdfs_principal";
    public static String HDFS_REALM = "hdfs_realm";
    public static String HDFS_KDC = "hdfs_kdc";
    public static String HDFS_PATTERN = "hdfs_pattern";
    public static String HDFS_ENDPOINT = "hdfs_endpoint";
    public static String HDFS_NAMESERVICE = "hdfs_nameservice";
    public static String HDFS_NAMENODE1 = "hdfs_namenode1";
    public static String HDFS_NAMENODE2 = "hdfs_namenode2";

    // spark related
    public static String SPARK_ENDPOINT = "spark_endpoint";

    // bigsql related
    public static String BIGSQL_ENDPOINT = "bigsql_endpoint";

    // iam related
    public static String IAM_ENDPOINT = "iam_endpoint";

    // sts related
    public static String STS_ENDPOINT = "sts_endpoint";

    // bcm related
    public static String BCM_ENDPOINT = "bcm_endpoint";

    // bos related
    public static String BOS_ENDPOINT = "bos_endpoint";
}
