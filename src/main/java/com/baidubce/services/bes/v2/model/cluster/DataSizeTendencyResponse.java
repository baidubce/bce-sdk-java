package com.baidubce.services.bes.v2.model.cluster;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

/**
 * Response for data size tendency.
 */
@Data
public class DataSizeTendencyResponse extends AbstractBceResponse {
    private String success;
    private Integer status;
    private Result result;

    @Data
    public static class Result {
        private String clusterId;
        private List<DataItem> data;
    }

    @Data
    public static class DataItem {
        private String byteSize;
        private List<IndexItem> indices;
        private String size;
        private String time;
        private String timeMills;
    }

    @Data
    public static class IndexItem {
        private String byteSize;
        private String index;
        private String size;
    }
}
