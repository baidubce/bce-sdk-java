package com.baidubce.services.aihc.model.inference;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
public class ListResPoolBriefResponse extends AbstractBceResponse {
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
        private List<ResPoolBriefInfo> resPools;

        @Getter
        @Setter
        public static class ResPoolBriefInfo {
            private String resPoolId;
            private String resPoolName;
            private String associatedPfsID;
            @JsonProperty("ClusterType")
            private String clusterType;
            @JsonProperty("Description")
            private String description;
            @JsonProperty("ForbidDelete")
            private boolean forbidDelete;
            @JsonProperty("K8sVersion")
            private String k8sVersion;
            private String createdAt;
            private String updatedAt;
            private String phase;

            @Override
            public String toString() {
                return "ResPoolBriefInfo{" + "\n" +
                        "\t\t resPoolId = " + resPoolId + "\n" +
                        "\t\t resPoolName = " + resPoolName + "\n" +
                        "\t\t associatedPfsID = " + associatedPfsID + "\n" +
                        "\t\t ClusterType = " + clusterType + "\n" +
                        "\t\t Description = " + description + "\n" +
                        "\t\t ForbidDelete = " + forbidDelete + "\n" +
                        "\t\t K8sVersion = " + k8sVersion + "\n" +
                        "\t\t createdAt = " + createdAt + "\n" +
                        "\t\t updatedAt = " + updatedAt + "\n" +
                        "\t\t phase = " + phase + "\n" +
                        "\t }";
            }
        }
    }

    @Override
    public String toString() {
        return "ListResPoolBriefResponse{" + "\n" +
                "\t errno = " + errno + "," + "\n" +
                "\t request_id = " + requestId + "," + "\n" +
                "\t data = " + data + "\n" +
                '}';
    }
}
