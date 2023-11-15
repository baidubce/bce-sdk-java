package com.baidubce.services.kafka.model.job;

/**
 * @Author: zounianjun
 * @Email: zounianjun@baidu.com
 * @Date: 2023/6/13
 * @Description:
 */
public enum JobType {
    UPDATE_ACCESS_CONFIG,
    REASSIGN_PARTITION,
    RESTART_KAFKA_CLUSTER,
    RESTART_BROKER,
    EXPAND_BROKER_DISK_CAPACITY,
    UPDATE_BROKER_NODE_TYPE,
    INCREASE_BROKER_COUNT
}
