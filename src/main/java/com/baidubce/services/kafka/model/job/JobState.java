package com.baidubce.services.kafka.model.job;

/**
 * @Author: zounianjun
 * @Email: zounianjun@baidu.com
 * @Date: 2023/6/13
 * @Description:
 */
public enum JobState {
    NEW,
    PENDING,
    RUNNING,
    SUSPENDED,
    CANCELLED,
    FINISHED,
    FAILED
}
