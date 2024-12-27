package com.baidubce.services.aihc.model.inference;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ResPoolDetailResponse extends AbstractBceResponse {
    /*
     * 错误码
     */
    private int errno;
    /*
     * 访问id
     */
    @JsonProperty("request_id")
    private String requestId;
    /*
     * data数据
     */
    private Data data;

    @Getter
    @Setter
    @ToString
    public static class Data {
        private ResPoolInfo resPool;
        private List<ResQueueInfo> resQueue;
    }

    @Getter
    @Setter
    @ToString
    public static class ResQueueInfo {
        private String name;
        private String nameSpace;
        private String parentQueue;
        private String queueType;
        private String state;
        private boolean reclaimable;
        private boolean preemptable;
        private Map<String, String> capability;
        private Map<String, String> allocated;
        private boolean disableOversell;
        private String createdTime;
    }

    @Getter
    @Setter
    @ToString
    public static class ResPoolInfo {
        private ResPoolMeta meta;
        private ResPoolSpec spec;
        private ResPoolStatus status;
    }

    @Getter
    @Setter
    @ToString
    public static class ResPoolMeta {
        private String resPoolId;
        private String resPoolName;
        private String createdAt;
        private String updatedAt;
    }

    @Getter
    @Setter
    @ToString
    public static class ResPoolSpec {
        private String k8sVersion;
        private String associatedPfsID;
        private String region;
        private List<String> associatedCpromIDs;
        private String createdBy;
        private String description;
        private boolean forbidDelete;
    }

    @Getter
    @Setter
    @ToString
    public static class ResPoolStatus {
        private String phase;
        @JsonProperty("GPUCount")
        private ResPoolCountInfo gpuCount;
        @JsonProperty("NodeCount")
        private ResPoolCountInfo nodeCount;
    }

    @Getter
    @Setter
    @ToString
    public static class ResPoolCountInfo {
        private int total;
        private int used;
    }

    @Override
    public String toString() {
        return "ResPoolDetailResponse{" + "\n" +
                "\t errno = " + errno + "," + "\n" +
                "\t request_id = " + requestId + "," + "\n" +
                "\t data = " + data + "\n" +
                '}';
    }
}
