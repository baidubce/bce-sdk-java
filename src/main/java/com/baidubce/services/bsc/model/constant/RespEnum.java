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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * enum in response
 */

@Slf4j
@Getter
@AllArgsConstructor
public enum RespEnum {
    /**
     * 常用状态枚举
     */
    SUCCESS(0, "操作成功!"),

    // 证书相关错误
    DUP_CERT(1001, "证书已存在!"),
    UPLOAD_CERT_FAILED(1002, "上传证书失败!"),
    CERT_NOT_FOUND(1003, "证书不存在"),

    // 代码相关错误
    COMMIT_CODE_NOT_FOUND(2001, "已发布代码不存在!"),
    CODE_NOT_FOUND(2002, "代码不存在!"),
    CODE_ERROR(2003, "代码语法错误!"),

    // 作业相关错误
    JOB_NOT_FOUND(3001, "作业不存在!"),
    JOB_IS_RUNNING(3002, "作业正在运行中!"),
    ANALYSIS_FAILED(3003, "解析表字段失败!"),
    UPLOAD_CSV_FAILED(3004, "上传CSV文件失败!"),
    DOWNLOAD_CSV_FAILED(3005, "下载CSV文件失败!"),
    OP_NOT_FOUND(3006, "运维详情不存在!"),
    RUN_OUT_OF_CU(3007, "CU已耗尽!"),
    SAVE_CSV_FAILED(3008, "保存CSV文件失败!"),
    GET_BCM_FAILED(3009, "获取BCM数据失败!"),
    GET_DEBUG_LOG_FAILED(3010, "获取调试日志失败!"),
    CREAT_PROJECT_FAILED(3011, "创建项目失败!"),
    JOB_IS_SUBMITTING(3012, "作业正在提交!"),
    JOB_STATUS_UNKNOWN(3013, "作业状态未知，无法启动"),
    DUP_JOB_NAME(3013, "作业名已存在！"),

    // HDFS相关错误
    UPLOAD_TO_HDFS_FAILED(4001, "上传文件失败!"),
    DOWNLOAD_FROM_HDFS_FAILED(4002, "下载文件失败!"),

    // App相关错误
    ADD_SINK_FAILED(5001, "添加SINK失败!"),
    ADD_SOURCE_FAILED(5002, "添加SOURCE失败!"),
    SUBMIT_APP_FAILED(5003, "提交APP失败!"),
    GET_APP_STATUS_FAILED(5004, "获取作业状态失败!"),
    KILL_APP_FAILED(5005, "停止作业失败!"),

    // 模板相关错误
    TEMPLATE_NOT_FOUND(6001, "模板不存在！"),

    // 日志相关错误
    EXECUTOR_LOG_NOT_FOUND(7001, "executor日志不存在！"),

    // error message相关错误
    ERROR_MSG_NOT_FOUND(8001, "报错信息不存在！");

    /**
     * 响应状态码
     */
    private int status;
    /**
     * 默认响应消息
     */
    private String msg;
}
