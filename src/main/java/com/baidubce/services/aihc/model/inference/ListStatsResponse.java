package com.baidubce.services.aihc.model.inference;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
public class ListStatsResponse extends AbstractBceResponse {
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
        private Map<String, StatsModel> apps;
    }

    @Getter
    @Setter
    public static class StatsModel {
        private int status;
        private int availableIns;
        private int totalIns;

        @Override
        public String toString() {
            return "StatsModel{" + "\n" +
                    "\t\t status = " + status + "\n" +
                    "\t\t availableIns = " + availableIns + "\n" +
                    "\t\t totalIns = " + totalIns + "\n" +
                    "\t }";
        }
    }

    @Override
    public String toString() {
        return "ListStatsResponse{" + "\n" +
                "\t errno = " + errno + "," + "\n" +
                "\t request_id = " + requestId + "," + "\n" +
                "\t data = " + data + "\n" +
                '}';
    }
}
