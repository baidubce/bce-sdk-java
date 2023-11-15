package com.baidubce.services.iotdmp.model.fm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.hadoop.mapred.TaskStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductConfigTask {
    private String instanceId;
    private String productKey;
    private String configId;
    private String taskId;
    private TaskStatus taskStatus;
    private int targetSize;
    private int completedSize;
    private int failedSize;
    private String createTime;
    private String updateTime;
}