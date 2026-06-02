package com.baidubce.services.bcc.model.task;


import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

import java.util.List;

@Data
public class ListTaskByMarkerRequest extends AbstractBceRequest {

    private Integer maxKeys = 10;

    private String marker = "-1";

    /**
     * 任务 ID。单次最多支持指定 100 个
     */
    private List<String> taskIds;

    private String taskAction;

    /**
     * 任务状态。取值范围：
     * * Finished：已完成。（非运行中）
     * * Processing：运行中。
     * * Failed：失败。（全部子任务失败）
     */
    private String taskStatus;

    /**
     * 主任务按创建时间查询，创建时间区间的起始点。按照ISO 8601标准表示，
     * 并需要使用 UTC +0 时间，格式为 yyyy-MM-ddTHH:mm:ssZ，比如2025-08-01T21:00:00Z。
     * 支持查近7天。
     */
    private String startTime;

    /**
     * 主任务按创建时间查询，创建时间区间的终止点。按照ISO 8601标准表示，
     * 并需要使用 UTC +0 时间，格式为 yyyy-MM-ddTHH:mm:ssZ，比如2025-08-01T21:00:00Z。
     */
    private String endTime;

    private List<String> resourceIds;


    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
